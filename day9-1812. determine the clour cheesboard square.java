class Solution {
    public boolean squareIsWhite(String coordinates) {
        
        char col = coordinates.charAt(0);
        char row = coordinates.charAt(1);

        int colNumber = col - 'a' + 1;
        int rowNumber = row - '0';

        // Returns true for white squares (odd sum) and false for black squares (even sum)
        return (colNumber + rowNumber) % 2 != 0;
    }
}
