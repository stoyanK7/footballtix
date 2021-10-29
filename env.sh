#!/bin/bash

title1="IntelliJ Idea"
title2="ReactJS"
title3="Maildev"
title4="VSCode"


gnome-terminal --tab --title="$title1" -e 'bash -c "cd /media/stoyank/Elements/University/Semester\ 3/footballtix; idea ."' \
               --tab --title="$title2" -e 'bash -c "cd /media/stoyank/Elements/University/Semester\ 3/footballtix/src/frontend; npm run start"' \
               --tab --title="$title3" --command="maildev" \
               --tab --title="$title4" -e 'bash -c "cd /media/stoyank/Elements/University/Semester\ 3/footballtix/src/frontend; code ."'
