package main;

import tile.TileManger;


public class Collisions {
    @SuppressWarnings("DuplicatedCode")
    public void checkCollision(character.Character character1) {
        int characterLeftX = character1.world_Xpos + character1.collisionBox.x;
        int characterRightX = character1.world_Xpos + character1.collisionBox.x +
                character1.collisionBox.y;
        int characterTopY = character1.world_Ypos + character1.collisionBox.y;
        int characterBottomY = characterTopY + character1.collisionBox.y;

        int characterLeftCol = characterLeftX/Window.tileSize;
        int characterRightCol = characterRightX/Window.tileSize;
        int characterTopRow = characterTopY/Window.tileSize;
        int characterBottomRow = characterBottomY/Window.tileSize;

        int tileNum1, tileNum2;

        switch (character1.direction){
            case ("up") -> {
                characterTopRow = (characterTopY - character1.speed)/Window.tileSize;
                tileNum1 = Window.tileManger.map[characterLeftCol][characterTopRow];
                tileNum2 = Window.tileManger.map[characterRightCol][characterTopRow];
                if (Window.tileManger.tiles[tileNum1].collision || Window.tileManger.tiles[tileNum2].collision) {
                    character1.collisionOn = true;
                }
            }
            case ("down") -> {
                characterBottomRow = (characterBottomY + character1.speed)/Window.tileSize;
                tileNum1 = Window.tileManger.map[characterLeftCol][characterBottomRow];
                tileNum2 = Window.tileManger.map[characterRightCol][characterBottomRow];
                if (Window.tileManger.tiles[tileNum1].collision || Window.tileManger.tiles[tileNum2].collision) {
                    character1.collisionOn = true;
                }
            }
            case ("left") -> {
                characterLeftCol = (characterLeftX - character1.speed)/Window.tileSize;
                tileNum1 = Window.tileManger.map[characterLeftCol][characterTopRow];
                tileNum2 = Window.tileManger.map[characterLeftCol][characterBottomRow];
                if (Window.tileManger.tiles[tileNum1].collision || Window.tileManger.tiles[tileNum2].collision) {
                    character1.collisionOn = true;
                }
            }
            case ("right") -> {
                characterRightCol = (characterRightX + character1.speed)/Window.tileSize;
                tileNum1 = Window.tileManger.map[characterRightCol][characterTopRow];
                tileNum2 = Window.tileManger.map[characterRightCol][characterBottomRow];
                if (Window.tileManger.tiles[tileNum1].collision || Window.tileManger.tiles[tileNum2].collision) {
                    character1.collisionOn = true;
                }
            }
        }
    }
}
