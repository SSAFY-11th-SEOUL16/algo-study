public class PG_아이템줍기 {
    private static final int SIZE = 51;
    private static final int MAX = SIZE * SIZE;

    private int start, characterDist, itemDist, dist;
    private int[] adj;

    private void addAdj(int[] rectangle) {
        int startX, startY, endX, endY, curr, i;

        startX = rectangle[0];
        startY = rectangle[1];
        start = Math.min(start, startX * SIZE + startY);
        endX = rectangle[2];
        endY = rectangle[3];
        for (i = startY; i < endY; i++) {
            curr = startX * SIZE + i;
            if (adj[curr] == 0) {
                adj[curr] = curr + 1;
            } else {
                adj[curr] = -1;
            }
        }
        for (i = startX; i < endX; i++) {
            curr = i * SIZE + endY;
            if (adj[curr] == 0) {
                adj[curr] = curr + SIZE;
            } else {
                adj[curr] = -1;
            }
        }
        for (i = endY; i > startY; i--) {
            curr = endX * SIZE + i;
            if (adj[curr] == 0) {
                adj[curr] = curr - 1;
            } else {
                adj[curr] = -1;
            }
        }
        for (i = endX; i > startX; i--) {
            curr = i * SIZE + startY;
            if (adj[curr] == 0) {
                adj[curr] = curr - SIZE;
            } else {
                adj[curr] = -1;
            }
        }
    }

    private void getDist(int characterX, int characterY, int itemX, int itemY) {
        int curr, next, character, item, dir;

        character = characterX * SIZE + characterY;
        item = itemX * SIZE + itemY;
        if (start == character) {
            characterDist = 0;
        } else if (start == item) {
            itemDist = 0;
        }
        next = 0;
        dir = 1;
        curr = start + 1;
        for (dist = 1; curr != start; dist++) {
            if (curr == character) {
                characterDist = dist;
            } else if (curr == item) {
                itemDist = dist;
            }
            if (adj[curr] > 0) {
                next = adj[curr];
            } else {
                switch (dir) {
                case 1:
                    next = curr - SIZE;
                    break;
                case SIZE:
                    next = curr + 1;
                    break;
                case -1:
                    next = curr + SIZE;
                    break;
                case -SIZE:
                    next = curr - 1;
                }
            }
            dir = next - curr;
            curr = next;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int i;

        adj = new int[MAX];
        start = MAX;
        for (i = 0; i < rectangle.length; i++) {
            addAdj(rectangle[i]);
        }
        getDist(characterX, characterY, itemX, itemY);
        return Math.min(Math.abs(itemDist - characterDist), dist - Math.abs(itemDist - characterDist));
    }
}