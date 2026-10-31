package com.droiddeck.launcher.gpu

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LinuxVulkanDriverImportTest {
    @Test fun anyMesaVulkanLibraryIsAccepted() {
        assertTrue(LinuxVulkanDriverManager.isVulkanLibrary("libvulkan_freedreno.so"))
        assertTrue(LinuxVulkanDriverManager.isVulkanLibrary("libvulkan_panfrost.so"))
        assertTrue(LinuxVulkanDriverManager.isVulkanLibrary("libvulkan_panfrost-glibc-aarch64.so"))
        assertFalse(LinuxVulkanDriverManager.isVulkanLibrary("libvulkan.so"))
        assertFalse(LinuxVulkanDriverManager.isVulkanLibrary("libvulkan_panfrost.so.txt"))
        assertFalse(LinuxVulkanDriverManager.isVulkanLibrary("meta.json"))
    }

    @Test fun bareElfIsToldApartFromAZip() {
        assertTrue(LinuxVulkanDriverManager.startsWithElf(byteArrayOf(0x7f, 'E'.code.toByte(), 'L'.code.toByte(), 'F'.code.toByte()), 4))
        assertFalse(LinuxVulkanDriverManager.startsWithElf(byteArrayOf('P'.code.toByte(), 'K'.code.toByte(), 3, 4), 4))
        assertFalse(LinuxVulkanDriverManager.startsWithElf(byteArrayOf(0x7f, 'E'.code.toByte(), 0, 0), 2))
    }
}
