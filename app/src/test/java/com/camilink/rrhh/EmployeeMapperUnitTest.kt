package com.camilink.rrhh

import com.camilink.rrhh.repository.service.EmployeeMapper
import org.junit.Test
import org.junit.Assert.*

class EmployeeMapperUnitTest {

    @Test
    fun `Correct parse`() {
        val jsonText =
            """{"Carlos Velasquez": {"id": 1,"position": "CEO","salary": "5","phone": "3109909090","email": "ceo@enterprise.com","upperRelation": 0},"Mateo Rodriguez": {"id": 2,"position": "CPO","salary": "4","phone": "3109909092","email": "cpo@enterprise.com","upperRelation": 1}}
        """

        val employees = EmployeeMapper.mapEmployees(jsonText)

        assertEquals(1, employees[0].id)

    }

    @Test(expected = EmployeeMapper.EmployeeMapperException::class)
    fun `Error parse`() {

        val jsonText = ""

        val employees = EmployeeMapper.mapEmployees(jsonText)


    }

}