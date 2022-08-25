# Rosewire

fabric mod to download files from the internet with a command on servers

requires fabric api

what files you can download must be configured manually with configuration files for security reasons

just create a folder ./config/rosewire/ and put files configuring all your downloads there

an example for a file like that is provided as example.txt

if you put that in ./config/rosewire/ and run /download example.txt it will download the file in your main server folder

as of version 2 files can also be unzipped automatically, example provided with examplezip.txt

you can change the path to anything in your server folder, so you can download like mods or datapacks with this

you can also have as many different download configurations as you'd like

made for 1.19.2, will probably be updated to never versions