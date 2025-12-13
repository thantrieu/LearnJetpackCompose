package pro.branium.learnjetpackcompose.lesson20.data.mapper

import pro.branium.learnjetpackcompose.lesson20.domain.model.Song
import pro.branium.learnjetpackcompose.lesson20.data.remote.dto.SongDto
import pro.branium.learnjetpackcompose.lesson20.data.local.SongEntity

fun SongDto.toDomain(): Song {
    return Song(
        id = id,
        title = title,
        album = album,
        artist = artist,
        sourceUrl = source,
        imageUrl = image,
        durationSec = duration,
        favorite = 1,
        playCount = counter,
        trackNumber = trackNumber
    )
}

fun Song.toDto(): SongDto {
    return SongDto(
        id = id,
        title = title,
        album = album ?: "",
        artist = artist ?: "",
        source = sourceUrl,
        image = imageUrl ?: "",
        duration = durationSec,
        favorite = favorite,
        counter = playCount,
        trackNumber = trackNumber
    )
}


fun Song.toEntity(): SongEntity {
    return SongEntity(
        id = id,
        title = title,
        album = album ?: "",
        artist = artist ?: "",
        sourceUrl = sourceUrl,
        imageUrl = imageUrl ?: "",
        durationSec = durationSec,
        favorite = favorite,
        playCount = playCount,
        trackNumber = trackNumber
    )
}

fun SongEntity.toDomain(): Song {
    return Song(
        id = id,
        title = title,
        album = album,
        artist = artist,
        sourceUrl = sourceUrl,
        imageUrl = imageUrl,
        durationSec = durationSec,
        favorite = favorite,
        playCount = playCount,
        trackNumber = trackNumber
    )
}

fun SongDto.toEntity(): SongEntity {
    return SongEntity(
        id = id,
        title = title,
        album = album,
        artist = artist,
        sourceUrl = source,
        imageUrl = image,
        durationSec = duration,
        favorite = favorite,
        playCount = counter,
        trackNumber = trackNumber
    )
}

fun List<SongDto>.dtoToDomainList(): List<Song> {
    return map { it.toDomain() }
}

fun List<Song>.toDtoList(): List<SongDto> {
    return map { it.toDto() }
}


fun List<SongDto>.toListEntity(): List<SongEntity> {
    return map { it.toEntity() }
}

fun List<SongEntity>.entityToDomainList(): List<Song> {
    return map { it.toDomain() }
}

fun List<Song>.toEntityList(): List<SongEntity> {
    return map { it.toEntity() }
}
