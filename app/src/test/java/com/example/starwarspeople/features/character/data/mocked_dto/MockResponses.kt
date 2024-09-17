package com.example.starwarspeople.features.character.data.mocked_dto

import com.example.starwarspeople.features.character.data.remote.CharacterDto
import com.example.starwarspeople.features.character.data.remote.CharacterListDto
import com.example.starwarspeople.features.character.data.remote.FilmDto
import com.example.starwarspeople.features.character.data.remote.HomeWorldDto
import com.example.starwarspeople.features.character.data.remote.SpecieDto

object MockResponses {
    val mockCharacterListDto = CharacterListDto(
        count = 1,
        next = null,
        previous = null,
        results = listOf(
            CharacterDto(
                name = "Luke Skywalker",
                height = "172",
                mass = "77",
                hairColor = "blond",
                skinColor = "fair",
                eyeColor = "blue",
                birthYear = "19BBY",
                gender = "male",
                homeworld = "https://swapi.dev/api/planets/1/",
                films = listOf(
                    "https://swapi.dev/api/films/1/",
                    "https://swapi.dev/api/films/2/",
                    "https://swapi.dev/api/films/3/",
                    "https://swapi.dev/api/films/6/"
                ),
                species = emptyList(),
                vehicles = listOf(
                    "https://swapi.dev/api/vehicles/14/",
                    "https://swapi.dev/api/vehicles/30/"
                ),
                starships = listOf(
                    "https://swapi.dev/api/starships/12/",
                    "https://swapi.dev/api/starships/22/"
                ),
                created = "2014-12-09T13:50:51.644000Z",
                edited = "2014-12-20T21:17:56.891000Z",
                url = "https://swapi.dev/api/people/1/"
            )
        )
    )

    val mockSpecieDto = SpecieDto(
        name="Human",
        classification="mammal",
        designation="sentient",
        averageHeight ="180",
        skinColors ="caucasian, black, asian, hispanic",
        hairColors ="blonde, brown, black, red",
        eyeColors ="brown, blue, green, hazel, grey, amber",
        averageLifespan ="120",
        homeworld="https://swapi.dev/api/planets/9/",
        language="Galactic Basic",
        people= listOf(
            "https://swapi.dev/api/people/66/",
            "https://swapi.dev/api/people/67/",
            "https://swapi.dev/api/people/68/",
            "https://swapi.dev/api/people/74/"
        ),
        films= listOf(
            "https://swapi.dev/api/films/1/",
            "https://swapi.dev/api/films/2/",
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/4/",
            "https://swapi.dev/api/films/5/",
            "https://swapi.dev/api/films/6/"
        ),
        created="2014-12-10T13:52:11.567000Z",
        edited="2014-12-20T21:36:42.136000Z",
        url="https://swapi.dev/api/species/1/"
    )

    val mockHomeWorldDto = HomeWorldDto(
        name="Tatooine",
        rotationPeriod ="23",
        orbitalPeriod ="304",
        diameter="10465",
        climate="arid",
        gravity="1 standard",
        terrain="desert",
        surfaceWater ="1",
        population="200000",
        residents= listOf(
            "https://swapi.dev/api/people/1/",
            "https://swapi.dev/api/people/2/",
            "https://swapi.dev/api/people/4/",
            "https://swapi.dev/api/people/6/",
            "https://swapi.dev/api/people/7/",
            "https://swapi.dev/api/people/8/",
            "https://swapi.dev/api/people/9/",
            "https://swapi.dev/api/people/11/",
            "https://swapi.dev/api/people/43/",
            "https://swapi.dev/api/people/62/"
        ),
        films= listOf(
            "https://swapi.dev/api/films/1/",
            "https://swapi.dev/api/films/3/",
            "https://swapi.dev/api/films/4/",
            "https://swapi.dev/api/films/5/",
            "https://swapi.dev/api/films/6/"
        ),
        created="2014-12-09T13:50:49.641000Z",
        edited="2014-12-20T20:58:18.411000Z",
        url="https://swapi.dev/api/planets/1/"
    )

    val mockFilmDto = FilmDto(
        title="A New Hope",
        episodeId =4,
        openingCrawl ="It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
        director="George Lucas",
        producer="Gary Kurtz, Rick McCallum",
        releaseDate ="1977-05-25",
        characters= listOf(
            "https://swapi.dev/api/people/1/",
            "https://swapi.dev/api/people/2/",
            "https://swapi.dev/api/people/3/",
            "https://swapi.dev/api/people/4/",
            "https://swapi.dev/api/people/5/",
            "https://swapi.dev/api/people/6/",
            "https://swapi.dev/api/people/7/",
            "https://swapi.dev/api/people/8/",
            "https://swapi.dev/api/people/9/",
            "https://swapi.dev/api/people/10/",
            "https://swapi.dev/api/people/12/",
            "https://swapi.dev/api/people/13/",
            "https://swapi.dev/api/people/14/",
            "https://swapi.dev/api/people/15/",
            "https://swapi.dev/api/people/16/",
            "https://swapi.dev/api/people/18/",
            "https://swapi.dev/api/people/19/",
            "https://swapi.dev/api/people/81/"
        ),
        planets= listOf(
            "https://swapi.dev/api/planets/1/",
            "https://swapi.dev/api/planets/2/",
            "https://swapi.dev/api/planets/3/"
        ),
        starships= listOf(
            "https://swapi.dev/api/starships/2/",
            "https://swapi.dev/api/starships/3/",
            "https://swapi.dev/api/starships/5/",
            "https://swapi.dev/api/starships/9/",
            "https://swapi.dev/api/starships/10/",
            "https://swapi.dev/api/starships/11/",
            "https://swapi.dev/api/starships/12/",
            "https://swapi.dev/api/starships/13/"
        ),
        vehicles= listOf(
            "https://swapi.dev/api/vehicles/4/",
            "https://swapi.dev/api/vehicles/6/",
            "https://swapi.dev/api/vehicles/7/",
            "https://swapi.dev/api/vehicles/8/"
        ),
        species= listOf(
            "https://swapi.dev/api/species/1/",
            "https://swapi.dev/api/species/2/",
            "https://swapi.dev/api/species/3/",
            "https://swapi.dev/api/species/4/",
            "https://swapi.dev/api/species/5/"
        ),
        created="2014-12-10T14:23:31.880000Z",
        edited="2014-12-20T19:49:45.256000Z",
        url="https://swapi.dev/api/films/1/"
    )
}

