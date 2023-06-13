package at.campus02.swd.game.gameobjects;

public class CurrentPositionObserver {
        private float lastX;
        private float lastY;

        public void update(Player player) {
            lastX = player.getPositionX();
            lastY = player.getPositionY();
        }

        public float getLastX() {
            return lastX;
        }

        public float getLastY() {
            return lastY;
        }
    }

