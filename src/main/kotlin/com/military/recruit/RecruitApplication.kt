package com.military.recruit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class RecruitApplication

fun main(args: Array<String>) {
	runApplication<RecruitApplication>(*args)
}