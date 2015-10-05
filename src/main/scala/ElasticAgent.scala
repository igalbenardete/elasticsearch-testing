import com.sksamuel.elastic4s.ElasticClient
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.mappings.FieldType._
import com.sksamuel.elastic4s.mappings.MappingDefinition
import entity.{Employee, Person, Student}
import org.elasticsearch.common.settings.ImmutableSettings

object ElasticAgent {

  val settings = ImmutableSettings.settingsBuilder()
    .put("http.enabled", true)
    .put("http.port", 12345)
    .put("path.home", "elasticsearch-data")

  val client = ElasticClient.local(settings.build)

  def createIndex() = {
    val indexIsExist = client.execute {
        clusterState
      }.await

    if (!indexIsExist.getState.routingTable().indicesRouting().containsKey("population")) {
      client.execute {
        create index "population" shards 1 replicas 1 mappings (
          "Student" as(
            "name" typed StringType,
            "age" typed IntegerType,
            "college" typed StringType,
            "birthDate" typed DateType,
            "currentProject" inner(
              "supervisor" typed StringType,
              "startDate" typed DateType,
              "duration" typed LongType
        ),
            "graduation" typed BooleanType
            )
        )
      }
    }
  }

  def createIndexWithMapping(mapping: MappingDefinition) = {
    client.execute {
      create index "population" shards 1 replicas 1 mappings mapping
    }
  }

  def addPerson[A <: Person](someone: A) = {
    someone match {
      case p: Employee =>
        client.execute {
          index into "population" / "Employee" source p
        }.await
      case s: Student =>
        client.execute {
          index into "population" / "Student" fields s.map
        }.await
    }
  }
  def deleteAllIndices() = client.execute(deleteIndex("_all")).await

//  val studentMapping =
//    )
//
//  "currentProject" inner(
//    "supervisor" typed StringType,
//    "startDate" typed DateType,
//    "duration" typed  LongType
//    ),
//

}
