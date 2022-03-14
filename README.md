# MuzStream
Description </br>
You are developing a music streaming application, MuzStream. One of the features is a play</br>
queue (referred to later as the playlist), much like Spotify's, where the songs that have been</br>
queued will automatically play. The application must have the following behavior:</br>
 The songs are considered as processes and are being played in an order determined by</br>
their priorities; i.e. the song with the highest priority is played out first, then the second</br>
highest priority, and so on;</br>
 The priorities will be determined by integer values, where the lower the value the higher</br>
the priority;</br>
 Songs with equal priorities are played out in any order;</br>
 The playlist is initially filled with playlistCapacity (given to the program through one</br>
of its arguments) songs taken sequentially from the listeners’ requests, which will be</br>
provided in an ASCII file (whose name is also given as an argument);</br>
 When placed in the playlist, the priority of a song is 0;</br>
 If the next song in the requests is already in the playlist, its priority increases by one;</br>
i.e. the integer value representing its priority is decremented by one;</br>
 Songs are removed from the playlist as they are played. When the size of the playlist</br>
reaches playlistLimit, an integer given as an argument representing a percentage</br>
of the full capacity (e.g. 20 for 20%), it is refilled by songs from the requests file; following</br>
the above priority policy;</br>
 The application will stop when the playlist is empty or after the playlist is filled</br>
numberOFillings, also an integer given as an argument, times;</br>
Each time the playlist is filled, the application releases the TOP-k, that is the k songs played the</br>
most often since the application was launched, from number 1 the song</br>
 that played the most, then the second most, and so on, to the kth; k is also given as an</br>
argument to the program.</br>
 For each song in the TOP-k, you must report the average time interval between two</br>
plays of the song.</br>
The program’s call with its argument is as the following:</br>
java MuzStream requests playlistCapacity playlistLimit numberOfFillings k</br>
Input</br>
requests (String) → the name of the file that contains the listeners’ requests (PS. this String</br>
contains the full path of the file). The songs must be read sequentially from this file.playlistCapacity (int) → the maximum number of songs that can be placed in the playlist</br>
playlistLimit (int) → the percentage of songs in the playlist, which when reached triggers</br>
the refilling</br>
numberOfFillings (int) → the number of times the playlist is refilled before the application</br>
stops (PS. the playlist will play until it reaches a load of playlistLimit percent for the last</br>
time after the last refill; which will also generate the last TOP-k; the last TOP-k is also generated</br>
when the playlist becomes empty; this happens when numberOfFillings has not been</br>
reached and there is no more songs in the playlist)</br>
Listeners’ requests file format</br>
Each line in the requests contains the following information:</br>
<artist><tab><title><tab><duration><newline></br>
where:</br>
<artist> is a String</br>
<title> is a String</br>
<duration> is an int (which gives the duration of the song in seconds)</br>
<tab> is the tabulation character</br>
<newline> is the end-of-line/carriage return character.</br>
Program call example</br>
java MuzStream listenersRequest01 4 25 2 3</br>
listenersRequest01:</br>
ArtistA Song0 300</br>
ArtistB Song1 500</br>
ArtistC Song2 450</br>
ArtistA Song0 300</br>
ArtistA Song3 250Behavior</br>
Initial playlist:</br>
[(-1, (ArtistA, Song0, 300), (0, (ArtistB, Song1, 500), (ArtistC, Song2, 450), (ArtistA, Song3, 250)]</br>
Playing ArtistA Song0 300</br>
Playlist: [(0, (ArtistB, Song1, 500), (ArtistC, Song2, 450), (ArtistA, Song3, 250)]</br>
Playing ArtistB Song1 500</br>
Playlist: [(ArtistC, Song2, 450), (ArtistA, Song3, 250)]</br>
Playing ArtistC Song2 450</br>
Playlist: [(ArtistA, Song3, 250)]</br>
Refill</br>
Playlist: [(ArtistA, Song3, 250)]</br>
Top3:</br>
ArtistA Song0 950</br>
ArtistB Song1 450</br>
ArtistC Song2 0</br>
Playing ArtistA Song3</br>
Playlist: []</br>
Top3:</br>
ArtistA Song0 1200</br>
ArtistB Song1 700</br>
ArtistC Song2 250</br>
N.B. The priority of ArtistA Song0 is -1 in the initial list because it was present the second time it</br>
was read from the requests file; it becomes the first song to be played. As each song has played</br>
only once in this situation, the average time between two plays becomes the time elapsed since</br>
the song has been played the first time (from its end).</br>
HintYou can use a sortable positional list for the TOP-k, similarly to what has already been done in</br>
the course. Do not forget to maintain the elapsed time since a song has been played.</br>
