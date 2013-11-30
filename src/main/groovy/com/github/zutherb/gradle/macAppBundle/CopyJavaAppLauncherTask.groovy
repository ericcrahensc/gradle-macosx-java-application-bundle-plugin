package com.github.zutherb.gradle.macAppBundle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class CopyJavaAppLauncherTask extends DefaultTask {

    @TaskAction
    def void writeStub() {
        def dest = project.file("${project.buildDir}/${project.macAppBundle.appOutputDir}/${project.macAppBundle.appName}.app/Contents/MacOS/${project.macAppBundle.bundleExecutable}")
        dest.parentFile.mkdirs()
        def outStream = new BufferedOutputStream(new FileOutputStream(dest))
        def buf = new byte[1024]
        def inStream = this.getClass().getClassLoader().getResourceAsStream("com/github/zutherb/gradle/mapAppBundle/JavaAppLauncher")
        if (inStream == null) throw new RuntimeException("Can't find resource for JavaAppLauncher in jar")
        int numRead = inStream.read(buf)
        while (numRead > 0) {
            outStream.write(buf, 0, numRead)
            numRead = inStream.read(buf)
        }
        inStream.close()
        outStream.close()
    }
}
