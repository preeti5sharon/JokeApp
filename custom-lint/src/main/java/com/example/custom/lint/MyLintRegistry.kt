package com.example.custom.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.example.custom.lint.detector.AndroidLogDetector
import com.example.custom.lint.detector.PrintlnDetector

@Suppress("UnstableApiUsage")
class MyLintRegistry : IssueRegistry() {
    override val issues = listOf(
        AndroidLogDetector.ISSUE,
        PrintlnDetector.ISSUE
    )

    override val api = CURRENT_API

    override val vendor = Vendor(LintConstant.Vendor)
}