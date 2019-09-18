package com.geno1024.servicerunner

import javax.swing.JFrame
import javax.swing.JPanel

object Main : JFrame("Service Runner")
{
    init
    {
        setSize(800, 600)
        defaultCloseOperation = EXIT_ON_CLOSE
        isVisible = true
    }

    @JvmStatic
    fun main(args: Array<String>)
    {
        Main
    }
}
