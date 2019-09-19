package com.geno1024.servicerunner

import com.sun.jna.platform.win32.Advapi32.INSTANCE as ADVAPI32
import com.sun.jna.platform.win32.Winsvc
import java.io.File
import java.io.IOException

object Windows : IOperation
{
    private val serviceManager = ADVAPI32.OpenSCManager(null, null, 0xF003F)

    override fun isAdmin(): Boolean = try
    {
        File.createTempFile("write", ".dll", File("C:\\Windows\\System32"))
        true
    }
    catch (e: IOException)
    {
        false
    }
    finally
    {
        File("C:\\Windows\\System32\\write.dll").delete()
    }

    override fun getServiceState(service: String): ServiceState = ServiceState.fromValue(
        Winsvc.SERVICE_STATUS().apply {
            ADVAPI32.QueryServiceStatus(
                ADVAPI32.OpenService(serviceManager, service, 0x0004),
                this
            )
        }.dwCurrentState
    )?:ServiceState.SERVICE_STOPPED

    override fun startService(service: String): Boolean = ADVAPI32.StartService(
        ADVAPI32.OpenService(serviceManager, service, 0x0010), 0, null
    )

    override fun stopService(service: String): Boolean = with (Winsvc.SERVICE_STATUS().apply {
        ADVAPI32.ControlService(
            ADVAPI32.OpenService(serviceManager, service, 0x0020), 0x00000001, this
        )
    }.dwCurrentState) {
        this == ServiceState.SERVICE_STOP_PENDING.value || this == ServiceState.SERVICE_STOPPED.value
    }

}
