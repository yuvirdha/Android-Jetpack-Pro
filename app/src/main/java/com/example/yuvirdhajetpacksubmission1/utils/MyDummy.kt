package com.example.yuvirdhajetpacksubmission1.utils

import com.example.yuvirdhajetpacksubmission1.data.source.local.entity.DataMovieEntity
import com.example.yuvirdhajetpacksubmission1.data.source.remote.response.DataMovieEntityResponse

object MyDummy {

    fun generateDummyMovies(): ArrayList<DataMovieEntity> {

        val dummyMovies = ArrayList<DataMovieEntity>()

        dummyMovies.add(DataMovieEntity(
                "The Shawshank Redemption",
                "1994",
                "Drama",
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        dummyMovies.add(DataMovieEntity(
                "The Godfather",
                "1972",
                "Crime | Drama",
                "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.",
                "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY268_CR3,0,182,268_AL_.jpg",
            false
        ))

        dummyMovies.add(DataMovieEntity(
                "The Dark Knight",
                "2008",
                "Action | Crime | Thriller",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))


        dummyMovies.add(DataMovieEntity(
                "Schindler's List",
                "1993",
                "Biography | History",
                "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.",
                "https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        dummyMovies.add(DataMovieEntity(
                "Inception",
                "2010",
                "Action | Adventure",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        dummyMovies.add(DataMovieEntity(
                "Interstellar",
                "2014",
                "Drama | Sci-Fi",
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        dummyMovies.add(DataMovieEntity(
                "Parasite",
                "2019",
                "Comedy | Thriller",
                "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.",
                "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        dummyMovies.add(DataMovieEntity(
                "Avengers: Endgame",
                "2019",
                "Adventure | Sci-Fi",
                "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        dummyMovies.add(DataMovieEntity(
                "Up",
                "2009",
                "Adventure | Family",
                "78-year-old Carl Fredricksen travels to Paradise Falls in his house equipped with balloons, inadvertently taking a young stowaway.",
                "https://m.media-amazon.com/images/M/MV5BMTk3NDE2NzI4NF5BMl5BanBnXkFtZTgwNzE1MzEyMTE@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))


        dummyMovies.add(DataMovieEntity(
                "WALL-E",
                "2008",
                "Adventure | Sci-Fi",
                "In the distant future, a small waste-collecting robot inadvertently embarks on a space journey that will ultimately decide the fate of mankind.",
                "https://m.media-amazon.com/images/M/MV5BMjExMTg5OTU0NF5BMl5BanBnXkFtZTcwMjMxMzMzMw@@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        return dummyMovies
    }


    //-----------------------------------------------------------------------generate TvShows---------------------------------------------------------


    fun generateDummyTvShows(): ArrayList<DataMovieEntity> {

        val dummyTvShows = ArrayList<DataMovieEntity>()

        dummyTvShows.add(DataMovieEntity(
                "Breaking Bad",
                "2008-2013",
                "Crime | Thriller",
                "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
                "https://m.media-amazon.com/images/M/MV5BMjhiMzgxZTctNDc1Ni00OTIxLTlhMTYtZTA3ZWFkODRkNmE2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY268_CR5,0,182,268_AL_.jpg",
            false
        ))


        dummyTvShows.add(DataMovieEntity(
                "Chernobyl",
                "2019",
                "History | Thriller",
                "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
                "https://m.media-amazon.com/images/M/MV5BZGQ2YmMxZmEtYjI5OS00NzlkLTlkNTEtYWMyMzkyMzc2MDU5XkEyXkFqcGdeQXVyMzQ2MDI5NjU@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        dummyTvShows.add(DataMovieEntity(
                "Game of Thrones",
                "2011",
                "Adventure | Fantasy",
                "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
                "https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_UY268_CR7,0,182,268_AL_.jpg",
            false
        ))

        dummyTvShows.add(DataMovieEntity(
                "Sherlock",
                "2010",
                "Crime | Drama",
                "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                "https://m.media-amazon.com/images/M/MV5BMWY3NTljMjEtYzRiMi00NWM2LTkzNjItZTVmZjE0MTdjMjJhL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNTQ4NTc5OTU@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))


        dummyTvShows.add(DataMovieEntity(
                "Death Note",
                "2006",
                "Mystery | Thriller",
                "An intelligent high school student goes on a secret crusade to eliminate criminals from the world after discovering a notebook capable of killing anyone whose name is written into it.",
                "https://m.media-amazon.com/images/M/MV5BODkzMjhjYTQtYmQyOS00NmZlLTg3Y2UtYjkzN2JkNmRjY2FhXkEyXkFqcGdeQXVyNTM4MDQ5MDc@._V1_UY268_CR1,0,182,268_AL_.jpg",
            false
        ))

        dummyTvShows.add(DataMovieEntity(
                "Attack on Titan",
                "2013 - Present",
                "Adventure | Fantasy",
                "After his hometown is destroyed and his mother is killed, young Eren Jaeger vows to cleanse the earth of the giant humanoid Titans that have brought humanity to the brink of extinction.",
                "https://m.media-amazon.com/images/M/MV5BMTY5ODk1NzUyMl5BMl5BanBnXkFtZTgwMjUyNzEyMTE@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        dummyTvShows.add(DataMovieEntity(
                "Apocalypse: The Second World War",
                "2009",
                "Documentary | History",
                "Apocalypse: The Second World War is a six-part French documentary about the Second World War. The documentary is composed exclusively of actual footage of the war as filmed by war correspondents, soldiers, resistance fighters and private citizens.",
                "https://m.media-amazon.com/images/M/MV5BYzNkNWY1OWYtYzRjNy00ZTZhLTg4ZTAtODg5YmI3OTFlMzY5XkEyXkFqcGdeQXVyNzQzNzQxNzI@._V1_UY268_CR3,0,182,268_AL_.jpg",
            false
        ))

        dummyTvShows.add(DataMovieEntity(
                "Crash Landing on You",
                "2019",
                "Comedy | Romance",
                "The absolute top secret love story of a chaebol heiress who made an emergency landing in North Korea because of a paragliding accident and a North Korean special officer who falls in love with her and who is hiding and protecting her.",
                "https://m.media-amazon.com/images/M/MV5BMzRiZWUyN2YtNDI4YS00NTg2LTg0OTgtMGI2ZjU4ODQ4Yjk3XkEyXkFqcGdeQXVyNTI5NjIyMw@@._V1_UY268_CR2,0,182,268_AL_.jpg",
            false
        ))

        dummyTvShows.add(DataMovieEntity(
                "Mr. Bean",
                "1990",
                "Comedy | Family",
                "Bumbling, childlike Mr. Bean has trouble completing the simplest of tasks in day-to-day life, but his perseverance and resourcefulness frequently allow him to find ingenious ways around problems.",
                "https://m.media-amazon.com/images/M/MV5BOGNjZTRlNDctNGI0Yi00YmFkLTljMmQtMjQ1ZjdiNmU5YTc0XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_UY268_CR0,0,182,268_AL_.jpg",
            false
        ))

        dummyTvShows.add(DataMovieEntity(
                "Stranger Things",
                "2016 - Present",
                "Sci-Fi | Thriller",
                "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.",
                "https://m.media-amazon.com/images/M/MV5BMjEzMDAxOTUyMV5BMl5BanBnXkFtZTgwNzAxMzYzOTE@._V1_UX182_CR0,0,182,268_AL_.jpg",
            false
        ))

        return dummyTvShows
    }

    fun generateRemoteDummyMovies(): ArrayList<DataMovieEntityResponse> {

        val dummyMovies = ArrayList<DataMovieEntityResponse>()

        dummyMovies.add(DataMovieEntityResponse(
                "The Shawshank Redemption",
                "1994",
                "Drama",
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                "https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        dummyMovies.add(DataMovieEntityResponse(
                "The Godfather",
                "1972",
                "Crime | Drama",
                "An organized crime dynasty's aging patriarch transfers control of his clandestine empire to his reluctant son.",
                "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY268_CR3,0,182,268_AL_.jpg"
        ))

        dummyMovies.add(DataMovieEntityResponse(
                "The Dark Knight",
                "2008",
                "Action | Crime | Thriller",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))


        dummyMovies.add(DataMovieEntityResponse(
                "Schindler's List",
                "1993",
                "Biography | History",
                "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.",
                "https://m.media-amazon.com/images/M/MV5BNDE4OTMxMTctNmRhYy00NWE2LTg3YzItYTk3M2UwOTU5Njg4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        dummyMovies.add(DataMovieEntityResponse(
                "Inception",
                "2010",
                "Action | Adventure",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        dummyMovies.add(DataMovieEntityResponse(
                "Interstellar",
                "2014",
                "Drama | Sci-Fi",
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                "https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        dummyMovies.add(DataMovieEntityResponse(
                "Parasite",
                "2019",
                "Comedy | Thriller",
                "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.",
                "https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        dummyMovies.add(DataMovieEntityResponse(
                "Avengers: Endgame",
                "2019",
                "Adventure | Sci-Fi",
                "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        dummyMovies.add(DataMovieEntityResponse(
                "Up",
                "2009",
                "Adventure | Family",
                "78-year-old Carl Fredricksen travels to Paradise Falls in his house equipped with balloons, inadvertently taking a young stowaway.",
                "https://m.media-amazon.com/images/M/MV5BMTk3NDE2NzI4NF5BMl5BanBnXkFtZTgwNzE1MzEyMTE@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))


        dummyMovies.add(DataMovieEntityResponse(
                "WALL-E",
                "2008",
                "Adventure | Sci-Fi",
                "In the distant future, a small waste-collecting robot inadvertently embarks on a space journey that will ultimately decide the fate of mankind.",
                "https://m.media-amazon.com/images/M/MV5BMjExMTg5OTU0NF5BMl5BanBnXkFtZTcwMjMxMzMzMw@@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        return dummyMovies
    }


    //-----------------------------------------------------------------------generate TvShows---------------------------------------------------------


    fun generateRemoteDummyTvShows(): ArrayList<DataMovieEntityResponse> {

        val dummyTvShows = ArrayList<DataMovieEntityResponse>()

        dummyTvShows.add(DataMovieEntityResponse(
                "Breaking Bad",
                "2008-2013",
                "Crime | Thriller",
                "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
                "https://m.media-amazon.com/images/M/MV5BMjhiMzgxZTctNDc1Ni00OTIxLTlhMTYtZTA3ZWFkODRkNmE2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY268_CR5,0,182,268_AL_.jpg"
        ))


        dummyTvShows.add(DataMovieEntityResponse(
                "Chernobyl",
                "2019",
                "History | Thriller",
                "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
                "https://m.media-amazon.com/images/M/MV5BZGQ2YmMxZmEtYjI5OS00NzlkLTlkNTEtYWMyMzkyMzc2MDU5XkEyXkFqcGdeQXVyMzQ2MDI5NjU@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        dummyTvShows.add(DataMovieEntityResponse(
                "Game of Thrones",
                "2011",
                "Adventure | Fantasy",
                "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
                "https://m.media-amazon.com/images/M/MV5BYTRiNDQwYzAtMzVlZS00NTI5LWJjYjUtMzkwNTUzMWMxZTllXkEyXkFqcGdeQXVyNDIzMzcwNjc@._V1_UY268_CR7,0,182,268_AL_.jpg"
        ))

        dummyTvShows.add(DataMovieEntityResponse(
                "Sherlock",
                "2010",
                "Crime | Drama",
                "A modern update finds the famous sleuth and his doctor partner solving crime in 21st century London.",
                "https://m.media-amazon.com/images/M/MV5BMWY3NTljMjEtYzRiMi00NWM2LTkzNjItZTVmZjE0MTdjMjJhL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNTQ4NTc5OTU@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))


        dummyTvShows.add(DataMovieEntityResponse(
                "Death Note",
                "2006",
                "Mystery | Thriller",
                "An intelligent high school student goes on a secret crusade to eliminate criminals from the world after discovering a notebook capable of killing anyone whose name is written into it.",
                "https://m.media-amazon.com/images/M/MV5BODkzMjhjYTQtYmQyOS00NmZlLTg3Y2UtYjkzN2JkNmRjY2FhXkEyXkFqcGdeQXVyNTM4MDQ5MDc@._V1_UY268_CR1,0,182,268_AL_.jpg"
        ))

        dummyTvShows.add(DataMovieEntityResponse(
                "Attack on Titan",
                "2013 - Present",
                "Adventure | Fantasy",
                "After his hometown is destroyed and his mother is killed, young Eren Jaeger vows to cleanse the earth of the giant humanoid Titans that have brought humanity to the brink of extinction.",
                "https://m.media-amazon.com/images/M/MV5BMTY5ODk1NzUyMl5BMl5BanBnXkFtZTgwMjUyNzEyMTE@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        dummyTvShows.add(DataMovieEntityResponse(
                "Apocalypse: The Second World War",
                "2009",
                "Documentary | History",
                "Apocalypse: The Second World War is a six-part French documentary about the Second World War. The documentary is composed exclusively of actual footage of the war as filmed by war correspondents, soldiers, resistance fighters and private citizens.",
                "https://m.media-amazon.com/images/M/MV5BYzNkNWY1OWYtYzRjNy00ZTZhLTg4ZTAtODg5YmI3OTFlMzY5XkEyXkFqcGdeQXVyNzQzNzQxNzI@._V1_UY268_CR3,0,182,268_AL_.jpg"
        ))

        dummyTvShows.add(DataMovieEntityResponse(
                "Crash Landing on You",
                "2019",
                "Comedy | Romance",
                "The absolute top secret love story of a chaebol heiress who made an emergency landing in North Korea because of a paragliding accident and a North Korean special officer who falls in love with her and who is hiding and protecting her.",
                "https://m.media-amazon.com/images/M/MV5BMzRiZWUyN2YtNDI4YS00NTg2LTg0OTgtMGI2ZjU4ODQ4Yjk3XkEyXkFqcGdeQXVyNTI5NjIyMw@@._V1_UY268_CR2,0,182,268_AL_.jpg"
        ))

        dummyTvShows.add(DataMovieEntityResponse(
                "Mr. Bean",
                "1990",
                "Comedy | Family",
                "Bumbling, childlike Mr. Bean has trouble completing the simplest of tasks in day-to-day life, but his perseverance and resourcefulness frequently allow him to find ingenious ways around problems.",
                "https://m.media-amazon.com/images/M/MV5BOGNjZTRlNDctNGI0Yi00YmFkLTljMmQtMjQ1ZjdiNmU5YTc0XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_UY268_CR0,0,182,268_AL_.jpg"
        ))

        dummyTvShows.add(DataMovieEntityResponse(
                "Stranger Things",
                "2016 - Present",
                "Sci-Fi | Thriller",
                "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.",
                "https://m.media-amazon.com/images/M/MV5BMjEzMDAxOTUyMV5BMl5BanBnXkFtZTgwNzAxMzYzOTE@._V1_UX182_CR0,0,182,268_AL_.jpg"
        ))

        return dummyTvShows
    }
}