# Requirements

## Spielfeld:
- Layer 0: Wasser, hardcoded
- Layer 1: Eis, reduziert sich im Laufe des Games
- Layer 2: Iglu, Spieler = Schneemann
- Layer 3: Win/Lose Animation
  - Der Layer 0 ist der für den Spieler optisch am tiefsten liegende Layer.
  - Die verbleibenden Layer liegen für den Spieler in aufsteigender Reihenfolge jeweils eine Ebene weiter im Vordergrund.

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
  - Als Spieler kann ich den Schneemann pro Runde um 1 Einheit über die Seitenränder der Kachel bewegen, auf der sich der Schneemann gerade befindet. Die Bewegung des Schneemanns über die Ecken der Kachel sind nicht erlaubt. 
  - Wenn der Schneemann nach erfolgtem Spielzug erneut auf einer Kachel mit Eis steht, überlebt der Schneemann. Im Anschluss schmilzt eine Kachel aus Eis auf der Spielfläche, dies kann nicht vom Spieler gesteuert werden.
  - Als Spieler kann ich mit dem Schneemann so lange erneut einen Spielzug ziehen, bis ich entweder die Kachel mit dem Iglu erreiche oder eine Kachel mit Wasser erreiche.
- Case 3: Win
  -	Das Spiel gilt als gewonnen, sobald der Schneemann das Iglu erreicht.
  -	Eine Konfetti Animation wird über das ganze Spielfeld gelegt.
- Case 4: Lose
  - Das Spiel gilt als verloren, sobald der Schneemann auf einer Kachel landet, auf der sich bereits Wasser befindet. In diesem Fall skaliert der Schneemann bis unendlich klein, es wirkt als würde er ertrinken und er ist Tod.
