# Rosewire

fabric mod to download files from the internet with a command

requires fabric api

made for servers but also works on client / singleplayer (as of version 3)

what files you can download must be configured manually with configuration files for security reasons

just create a folder ./config/rosewire/ and put files configuring all your downloads there

an example for a file like that is provided as example.txt

if you put that in ./config/rosewire/ and run /download example.txt it will download the file in your main server folder

as of version 2 files can also be unzipped automatically, example provided with examplezip.txt

you can change the path to anything in your server folder, so you can download like mods or datapacks with this

you can also have as many different download configurations as you'd like

files can also be downloaded pre-launch by specifying the downloads in rosewire.prelaunch in ./config/