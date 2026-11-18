import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class Exercicio4 {

    public static void inserirLoteLog(Connection conn, List<String> mensagensLog) {
        String sql = "INSERT INTO logs_sistema (mensagem, data_registro) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);

            for (String mensagem : mensagensLog) {
                pstmt.setString(1, mensagem);
                pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                pstmt.addBatch(); 
            }

            int[] resultados = pstmt.executeBatch();
            conn.commit();

            System.out.println("Lote enviado. Registros inseridos: " + resultados.length);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir lote: " + e.getMessage());
            try {
                conn.rollback(); 
            } catch (SQLException ex) {
                System.out.println("Erro no rollback: " + ex.getMessage());
            }
        }
    }
}
