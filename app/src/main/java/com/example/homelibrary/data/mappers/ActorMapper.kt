package com.example.homelibrary.data.mappers

import com.example.homelibrary.data.local_db.actors.ActorEntity
import com.example.homelibrary.data.local_db.actors.KnownForEntity
import com.example.homelibrary.data.remote.response.actors.ActorDto
import com.example.homelibrary.data.remote.response.actors.KnownForDto
import com.example.homelibrary.domain.model.Actor
import com.example.homelibrary.domain.model.KnownFor

fun ActorEntity.toActor(): Actor {
    return Actor(
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        popularity = popularity,
        profilePath = profilePath,
        knownFor = knownFor.map { it.toKnownFor(id) }
    )
}
fun KnownForEntity.toKnownFor(
    actorId: Int
): KnownFor {
    return KnownFor(
        id,
        actorId,
        backdropPath,
        mediaType,
        name,
        title,
        voteAverage
    )
}


fun ActorDto.toActorEntity(): ActorEntity {
    return ActorEntity(
        id = id,
        knownFor = known_for.map { it.toKnownForEntity(id) },
        knownForDepartment = known_for_department,
        name = name,
        popularity = popularity,
        profilePath = profile_path
    )
}
fun KnownForDto.toKnownForEntity(
    actorId: Int
): KnownForEntity {
    return KnownForEntity(
        id = id,
        backdropPath =  backdrop_path,
        mediaType = media_type,
        name = name,
        title = title,
        voteAverage = vote_average,
        actorId = actorId
    )
}