package com.geno1024.servicerunner

import com.sun.jna.platform.win32.Advapi32Util
import com.sun.jna.platform.win32.WinNT
import com.sun.jna.platform.win32.Winsvc
import java.awt.*
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.system.exitProcess
import com.sun.jna.platform.win32.Advapi32.INSTANCE as ADVAPI32
import java.io.IOException
import java.io.File



object Main : JFrame("Service Runner")
{
    init
    {
        System.load("C:\\Windows\\Microsoft.NET\\Framework64\\v4.0.30319\\System.ServiceProcess.dll")
        setSize(800, 600)
        defaultCloseOperation = EXIT_ON_CLOSE
        setDefaultLookAndFeelDecorated(true)
//        isVisible = true
        if (SystemTray.isSupported())
        {
            SystemTray.getSystemTray()
                .add(
                    TrayIcon(
                        Toolkit.getDefaultToolkit().getImage(null as? String?).getScaledInstance(16, 16, Image.SCALE_DEFAULT),
                        "Service Runner",
                        PopupMenu().apply {
                            add(MenuItem("Hello").apply {
                                addActionListener {
                                    if (!isShowing) isVisible = true
                                }
                            })
                            add(MenuItem("Exit").apply {
                                addActionListener {
                                    exitProcess(0)
                                }
                            })
                        }
                    )
                )
        }
        add(JPanel().apply {
        })
    }

    @JvmStatic
    fun main(args: Array<String>)
    {
//        println(getStatusOf("mariadb"))
//        println(stopService("mariadb"))
//        println(KERNEL32.GetLastError())
//            println(startService("mariadb"))
//        println(getStatusOf("mariadb"))
    }
}
