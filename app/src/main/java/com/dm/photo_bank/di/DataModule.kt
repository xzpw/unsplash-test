package com.dm.photo_bank.di

import com.dm.photo_bank.domain.ListRepo
import org.koin.dsl.module

val dataModule = module {
    factory { ListRepo(get()) }
}