# exoplayer_temp
An Android app to play a "song.mp3" file present in the root directory of SD Card using AssetDataSource where a custom InputStream named "myInputStream" is provided.
Currently exoplayer does not support any datasource where we can feed an inputstream directly to play an audio file.
This is an example where "myDataSource" (similar to AssetDataSource) uses "myInputStream" to play an audio file in the form of bytes. 
