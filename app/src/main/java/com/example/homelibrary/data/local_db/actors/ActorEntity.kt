package com.example.homelibrary.data.local_db.actors

import androidx.room.*

data class ActorKnownForCrossRef(
    @Embedded val actor: ActorEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "actorId"
    )
    val knownFor: List<KnownForEntity>

)

@Entity(tableName = "actor")
data class ActorEntity(
    @PrimaryKey
    val id: Int,

    val knownFor: List<KnownForEntity>,
    val knownForDepartment: String,
    val name: String,
    val popularity: Double,
    val profilePath: String
)

@Entity(
    tableName = "known_for",
    foreignKeys = [
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["id"],
            childColumns = ["actorId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("actorId")]
)
data class KnownForEntity(
    @PrimaryKey
    val id: Int,
    val actorId: Int, //foreign key

    val backdropPath: String,
    val mediaType: String,
    val name: String,
    val title: String,
    val voteAverage: Double
)
