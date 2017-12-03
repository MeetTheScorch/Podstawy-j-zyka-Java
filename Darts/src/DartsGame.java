/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MeetTheScorch
 */
public class DartsGame {
    private int firstPlayerPoints = 301;
    private int secondPlayerPoints = 301;

    public void setFirstPlayerPoints(int firstPlayerPoints) { this.firstPlayerPoints = firstPlayerPoints; }
    public void setSecondPlayerPoints(int secondPlayerPoints) { this.secondPlayerPoints = secondPlayerPoints; }
    public int getFirstPlayerPoints() { return firstPlayerPoints; }
    public int getSecondPlayerPoints() { return secondPlayerPoints; }
    
    public int getPlayerPoints(int player){
        return (player == 1) ?  firstPlayerPoints : secondPlayerPoints;
    }
    
    public void turn(int player, int points, int multiplier){
        checkValues(player,points,multiplier);
        if(player == 1){
            setFirstPlayerPoints(shoot(player,points,multiplier));
        }
        else{
            setSecondPlayerPoints(shoot(player,points,multiplier));
        }
        isWinner(player);
    }

    private int shoot(int player, int points, int multiplier) {
        System.out.println("Player "+player+" threw "+points+" x "+multiplier);
        if(points * multiplier <= getPlayerPoints(player))
            return getPlayerPoints(player) - points * multiplier;
        else
            return getPlayerPoints(player);
    }

    private void isWinner(int player) {
        System.out.println("Player 1 points:"+getPlayerPoints(1));
        System.out.println("Player 2 points:"+getPlayerPoints(2));
        if(getPlayerPoints(player) == 0)
            System.out.println("Player "+player+" is a winner!");
        System.out.println();
    }
    
    private void checkValues(int player, int points, int multiplier)
    {
        checkPlayer(player);
        checkPoints(points);
        checkMultiplier(multiplier);
    }

    private void checkPlayer(int player) {
        if(player != 1 && player != 2)
            throw new IllegalArgumentException("Wrong player.");
    }

    private void checkPoints(int points) {
        if(points < 1 || points > 20)
            throw new IllegalArgumentException("Wrong points.");
    }

    private void checkMultiplier(int multiplier) {
        if(multiplier < 1 || multiplier > 3)
            throw new IllegalArgumentException("Wrong multiplier.");
    }
}
