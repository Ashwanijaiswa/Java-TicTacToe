import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeApp extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char currentPlayer;
    private JLabel statusLabel;

    public TicTacToeApp() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 3));

        buttons = new JButton[3][3];
        currentPlayer = 'X';

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        // Add status label
        statusLabel = new JLabel("Player " + currentPlayer + "'s turn");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(statusLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        // Find the clicked button's position
        int row = -1, col = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == buttonClicked) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Check if the button is already clicked or not
        if (!buttonClicked.getText().equals("")) {
            return;
        }

        // Set X or O
        buttonClicked.setText(String.valueOf(currentPlayer));

        // Check if there is a winner
        if (checkWinner(row, col)) {
            statusLabel.setText("Player " + currentPlayer + " wins!");
            disableAllButtons();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a draw!");
            disableAllButtons();
        } else {
            switchPlayer();
            statusLabel.setText("Player " + currentPlayer + "'s turn");
        }
    }

    private boolean checkWinner(int row, int col) {
        String symbol = String.valueOf(currentPlayer);

        // Check row
        if (buttons[row][0].getText().equals(symbol) &&
            buttons[row][1].getText().equals(symbol) &&
            buttons[row][2].getText().equals(symbol)) {
            return true;
        }

        // Check column
        if (buttons[0][col].getText().equals(symbol) &&
            buttons[1][col].getText().equals(symbol) &&
            buttons[2][col].getText().equals(symbol)) {
            return true;
        }

        // Check diagonal
        if (buttons[0][0].getText().equals(symbol) &&
            buttons[1][1].getText().equals(symbol) &&
            buttons[2][2].getText().equals(symbol)) {
            return true;
        }

        // Check anti-diagonal
        if (buttons[0][2].getText().equals(symbol) &&
            buttons[1][1].getText().equals(symbol) &&
            buttons[2][0].getText().equals(symbol)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToeApp();
            }
        });
    }
}

