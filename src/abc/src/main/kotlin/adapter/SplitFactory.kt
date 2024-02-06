package adapter

import domain.entities.User
import domain.entities.ExpenseType
import domain.entities.Split
import domain.usecases.internal.EqualSplit

class SplitFactory {
    companion object {
        fun createSplit(type: ExpenseType, user: User, amount: Double, amountOrPercent: Double): Split {
            return when(type) {
                ExpenseType.EQUAL -> EqualSplit(user, amount)
                ExpenseType.PERCENT -> TODO()
                ExpenseType.EXACT -> TODO()
                else -> TODO()
            }
        }
    }
}