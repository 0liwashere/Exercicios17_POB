import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Exercicio3 {

    public static void atualizarSalario(Connection conn, Long id, double novoSalario) {
        String sql = "UPDATE funcionarios SET salario = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, novoSalario);
            pstmt.setLong(2, id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Nenhum registro encontrado com o ID informado.");
            } else {
                System.out.println("Salario atualizado. Linhas afetadas: " + linhasAfetadas);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar salario: " + e.getMessage());
        }
    }

    public static void deletarPorId(Connection conn, Long id) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            int linhasAfetadas = pstmt.executeUpdate();

            if (linhasAfetadas == 0) {
                System.out.println("Nenhum registro encontrado com o ID informado.");
            } else {
                System.out.println("Funcionario deletado. Linhas afetadas: " + linhasAfetadas);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar funcionario: " + e.getMessage());
        }
    }
}
