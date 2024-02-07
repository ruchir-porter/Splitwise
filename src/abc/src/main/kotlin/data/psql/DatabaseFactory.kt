package data.psql

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import data.psql.tables.BalancesTable
import data.psql.tables.GroupTable
import data.psql.tables.UserTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    init {
        val dataSource = hikari()
        Database.connect(dataSource)
        transaction {
            SchemaUtils.create(UserTable)
            SchemaUtils.create(GroupTable)
            SchemaUtils.create(BalancesTable)
        }

    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
//        config.driverClassName = System.getenv("JDBC_DRIVER")
//        config.jdbcUrl = System.getenv("JDBC_DATABASE_URL")
        config.jdbcUrl = "jdbc:postgresql://localhost:5432/splitwise"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"

        return HikariDataSource(config)
    }

    suspend fun <T> dbQuery(block: () -> T): T =
        withContext(Dispatchers.IO) {
            transaction {
                block()
            }
        }
}

suspend fun main() {
//    val db = DatabaseFactory
//    bk.addUser("ruchir", "abc@zee.com")
//    val user1 = bk.getUser(1)
//    println(user1)
//    bk.createGroup("g1", listOf(1,2))
//    val g1 = bk.getGroup(1)
//    println(g1)
//    bk.addExpense(1,2,1, 100.0)

}