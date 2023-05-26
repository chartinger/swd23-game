# Requirements

## Spielfeld:
- Layer 0: Wasser
- Layer 1: Eis, reduziert sich im Laufe des Games
- Layer 2: Zielfeld = Iglu, Spieler = Schneemann

Der Layer 0 ist der für den Spieler optisch am tiefsten liegende Layer.
Die verbleibenden Layer liegen für den Spieler in aufsteigender Reihenfolge jeweils eine Ebene weiter im Vordergrund.

## Spieler:
-	Schneemann: 
  - Spielfigur/Held (kämpft ums Überleben), kann durch den Spieler via Cursortasten bewegt werden 
  - Die Kachel, auf der Schneemann steht, schmilzt nie.

## Items:
- Iglu:
  - Steht auf einer Eisfläche
  - Die Eisfläche, auf der das Iglu steht, schmilzt nie

## Game Cases:
- Case 1: Ausgangsszenario
  - Ich bin ein Schneemann und befinde mich auf einer Eisfläche.
  - Die Eisfläche schmilzt, ich muss das rettende Iglu erreichen. Sonst sterbe ich.
- Case 2: Spielrunde
  - Als Spieler muss ich den Schneemann pro Runde um 1 Einheit bewegen. Eine Bewegung ist nur über die Seitenränder der Kachel auf der sich der Schneemann gerade befindet möglich. Die Bewegung des Schneemanns über die Ecken der Kachel (diagonal) ist nicht möglich. 
  - Wenn der Schneemann nach erfolgtem Spielzug erneut auf einer Kachel mit Eis steht, überlebt der Schneemann. Im Anschluss schmilzt Eis auf der Spielfläche. Dies kann nicht vom Spieler gesteuert werden.
  - Als Spieler kann ich mit dem Schneemann so lange erneut ziehen, bis ich entweder die Kachel mit dem Iglu oder eine Kachel mit Wasser erreiche.
- Case 3: Win
  -	Das Spiel gilt als gewonnen, sobald der Schneemann das Iglu erreicht.
- Case 4: Loose
  - Das Spiel gilt als verloren, sobald der Schneemann im Wasser landet.

## Eye Candy (optional):

### Win/Loose Animations:
- Case 3: Win 
  - Eine Konfetti-Animation wird über das ganze Spielfeld gelegt.
- Case 4: Loose 
  - In diesem Fall skaliert der Schneemann bis unendlich klein, es wirkt als würde er ertrinken und er ist Tod.
