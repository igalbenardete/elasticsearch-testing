import entity._
import enumaration.College.Kocaeli
import enumaration.Company.{Google, Cmak}
import org.elasticsearch.common.joda.time.DateTime

object ElasticTesting {
  def main(args: Array[String]) {
    val manager = Employee("Onur", 31, Cmak, None)
    val projectOne = Project("Hoca Ali", DateTime.now.minusDays(7), 90000)

    val people: Seq[Person] = Seq(
      manager,
      Employee("Igal", 25, Cmak, Some(manager)),
      Employee("Beril", 24, Cmak, Some(manager)),
      Student("Sait", 23, Kocaeli, DateTime.now, projectOne, graduationStatus = false),
      Student("Oguzhan", 23, Kocaeli, DateTime.now.minusYears(23), projectOne, graduationStatus = false)
    )

    val employees = Seq(
      manager,
      Employee("Igal", 25, Cmak, Some(manager)),
      Employee("Beril", 24, Cmak, Some(manager))
    )

    val boss = Manager("Igal", 24, "Boss", Google, employees)

    val projectTwo = Project("HocaVeli", DateTime.now.minusMonths(1), 40000)

    val students = Seq(
      Student("Sait", 23, Kocaeli, DateTime.now, projectOne, graduationStatus = false),
      Student("Oguzhan", 23, Kocaeli, DateTime.now.minusYears(23),projectTwo, graduationStatus = false)
    )


    val s = Student("Sait", 23, Kocaeli, DateTime.now, projectTwo, graduationStatus = false)
    println("HELLO")
    ElasticAgent.deleteAllIndices()
    ElasticAgent.createIndex()
    ElasticAgent.addPerson(boss)
  }
}
