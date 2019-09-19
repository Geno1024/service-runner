package com.geno1024.servicerunner

interface IOperation
{
    fun isAdmin(): Boolean

    fun getServiceState(service: String): ServiceState

    fun startService(service: String): Boolean

    fun stopService(service: String): Boolean
}
