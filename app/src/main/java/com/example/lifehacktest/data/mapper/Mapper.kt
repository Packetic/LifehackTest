package com.example.lifehacktest.data.mapper

import com.example.lifehacktest.data.database.OrganizationDbModel
import com.example.lifehacktest.data.database.OrganizationDetailsDbModel
import com.example.lifehacktest.data.network.model.OrganizationDetailsDto
import com.example.lifehacktest.data.network.model.OrganizationDto
import com.example.lifehacktest.domain.Organization
import com.example.lifehacktest.domain.OrganizationDetails

class Mapper {
    fun mapOrganizationDtoToDbModel(dto: OrganizationDto) = OrganizationDbModel(
        id = dto.id,
        img = dto.img,
        name = BASE_IMAGE_URL + dto.name
    )

    fun mapOrganizationDetailsDtoToDbModel(dto: OrganizationDetailsDto) =
        OrganizationDetailsDbModel(
            description = dto.description,
            id = dto.id,
            img = BASE_IMAGE_URL + dto.img,
            lat = dto.lat,
            lon = dto.lon,
            name = dto.name,
            phone = dto.phone,
            www = dto.www
        )

    fun mapOrganizationDbModelToEntity(dbModel: OrganizationDbModel) = Organization(
        id = dbModel.id,
        img = dbModel.img,
        name = dbModel.name
    )

    fun mapOrganizationDetailsDbModelToEntity(dbModel: OrganizationDetailsDbModel) =
        OrganizationDetails(
            description = dbModel.description,
            id = dbModel.id,
            img = dbModel.img,
            lat = dbModel.lat,
            lon = dbModel.lon,
            name = dbModel.name,
            phone = dbModel.phone,
            www = dbModel.www
        )

    companion object {
        private const val BASE_IMAGE_URL = "https://lifehack.studio/test_task/"
    }
}