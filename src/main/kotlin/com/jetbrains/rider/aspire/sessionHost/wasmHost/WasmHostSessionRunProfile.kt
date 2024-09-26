package com.jetbrains.rider.aspire.sessionHost.wasmHost

import com.intellij.execution.Executor
import com.intellij.execution.process.ProcessListener
import com.intellij.execution.runners.ExecutionEnvironment
import com.jetbrains.rd.util.lifetime.Lifetime
import com.jetbrains.rider.aspire.sessionHost.projectLaunchers.ProjectSessionRunProfile
import com.jetbrains.rider.aspire.sessionHost.projectLaunchers.ProjectSessionRunProfileState
import com.jetbrains.rider.runtime.DotNetExecutable
import com.jetbrains.rider.runtime.dotNetCore.DotNetCoreRuntime

class WasmHostSessionRunProfile(
    private val sessionId: String,
    projectName: String,
    private val dotnetExecutable: DotNetExecutable,
    private val dotnetRuntime: DotNetCoreRuntime,
    private val sessionProcessEventListener: ProcessListener,
    private val sessionProcessTerminatedListener: ProcessListener,
    private val sessionProcessLifetime: Lifetime
) : ProjectSessionRunProfile(projectName) {
    override fun getState(
        executor: Executor,
        environment: ExecutionEnvironment
    ) = ProjectSessionRunProfileState(
        sessionId,
        dotnetExecutable,
        dotnetRuntime,
        environment,
        sessionProcessEventListener,
        sessionProcessTerminatedListener,
        sessionProcessLifetime
    )
}