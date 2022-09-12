package com.mh.mhapp.data.model

import com.google.gson.annotations.SerializedName

data class NameUrl(

    @SerializedName("name")
    var name: String,

    @SerializedName("url")
    var url: String
)

data class GameIndices(

    @SerializedName("game_index")
    var game_index: Int,

    @SerializedName("version")
    var version: NameUrl
)

data class VersionGroupDetails(
    @SerializedName("move_learn_method")
    var move_learn_method: NameUrl
)

data class Move(
    @SerializedName("version_group_details")
    var version_group_details: Int,
)

data class Moves(

    var index: Int,

    @SerializedName("version_group_details")
    var version_group_details: List<VersionGroupDetails>
)

data class RequestPokemon(

    // { Tipo de dato Objecto }
    // [ Lista de objetos o de datos simples]

    @SerializedName("id")
    var id: Int,

    @SerializedName("weight")
    var weight: Int,

    @SerializedName("species")
    var species: NameUrl,

    @SerializedName("forms")
    var forms: List<NameUrl>,

    // name
    @SerializedName("name")
    var name: String,

    // game_indices -> version -> name
    @SerializedName("game_indices")
    var game_indices: List<GameIndices>,

    // moves -> version_group_details -> move_learn_method -> name
    @SerializedName("moves")
    var moves: List<Moves>
)
