LifeSimulation it is a 2D game where creatures in custom world try to survive. Evolving mechanism allows creatures turn their predators in victims.

The map consist of:

1. Static objects : 🗻🌲
2. DNA for evolution : 🧬
3. Active creatures : 🦎👺

Active creatures after collecting 3 DNA are evolving :
1. 🦎 -> 🐉
2. 👺 -> 😈

Game has the following power hierarchy :
😈 > 🐉 > 👺 > 🦎

Each creature has 2 move per round and can spend their move either for move toward target or attack the target.

Creatures use algorithm A* for finding its target and spawn selector uses algorithm BFS for finding not blocked cells.

![image](https://github.com/user-attachments/assets/a8b2a2c7-c5b6-4340-82c7-4f98a7e50db2)
