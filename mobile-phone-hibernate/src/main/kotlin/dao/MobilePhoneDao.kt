package dao

import entity.MobilePhone
import org.hibernate.SessionFactory

class MobilePhoneDao(val sessionFactory: SessionFactory) {


    fun save(mobilePhone: MobilePhone): Long {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            val phoneId = session.save(mobilePhone) as Long
            session.transaction.commit()
            return phoneId
        }
    }

    fun update(mobilePhone: MobilePhone) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.update(mobilePhone)
            session.transaction.commit()
        }
    }

    fun findById(id: Long): MobilePhone? {
        val result: MobilePhone?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.get(MobilePhone::class.java, id)
            session.transaction.commit()
        }
        return result
    }


    fun findByName(phone_name: String): MobilePhone? {
        val result: MobilePhone?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result =
                session.byNaturalId(MobilePhone::class.java).using("phone_name", phone_name).loadOptional().orElse(null)
            session.transaction.commit()
        }
        return result
    }

    fun findAllByProcessor(processor : Int): List<MobilePhone> {
        val result: List<MobilePhone>
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.createQuery("from mobilephone where processor = $processor").list() as List<MobilePhone>
            session.transaction.commit()
        }
        return result
    }


    fun findAll(): List<MobilePhone> {
        val result: List<MobilePhone>
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.createQuery("from mobilephone").list() as List<MobilePhone>
            session.transaction.commit()
        }
        return result
    }

    fun delete(mobilePhone: MobilePhone) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.delete(mobilePhone)
            session.transaction.commit()
        }
    }
}

