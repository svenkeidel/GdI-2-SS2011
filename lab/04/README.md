README
======

Wir parsen die TODO Datein mit [Markdown](http://daringfireball.net/projects/markdown/).

Um es auf Ubuntu zu installieren gibt man folgendes in die Kommandozeile
ein:

`` $ sudo apt-get install markdown ``

Um das TODO oder das README zu parsen, gibt man folgendes ein:

`` $ markdown TODO.md > TODO.html ``
<br>
`` $ firefox TODO.html ``

Ich habe einen `*.html` Eintrag in die `.gitignore`-Datei geschrieben,
da das html aus der Markdown Datei erzeugt werden kann.
