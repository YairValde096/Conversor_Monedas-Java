import java.sql.*;
public class MySQLConnector {
    private String DB_URL;
    //Database credentials
    private String USER;
    private String PASS;
    protected Connection conn;
    
   // "127.0.0.1:3306/tabla_conversion","root","" Si se desea usar otra conexion; cambiar los valores del constructor.

    public MySQLConnector(){
        DB_URL = "jdbc:mysql://localhost:3306/tabla_conversion";
        USER = "root";
        PASS = "";
        conn = null;
    }
    public void openConnection(){
        try{
            //Register JDBC driver
            //Class.forName(JDBC_DRIVER);         
            //Open a connection
            System.out.print("Connecting to a selected database... ");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Success!");     
        }catch(Exception e){
            //Handle errors for JDBC
            e.printStackTrace();
        }
        
    }
    public void closeConnection(){
        try{
            if(conn!=null)
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        System.out.println("Connection closed");
    }
    public Connection getConnection(){
        return conn;
    }
    public ResultSet getData(String tableName) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        return resultSet;
    }
    public double getData(String tableName, String columna, int buscador) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE ID_Costo =" + buscador);

        double numero = 0.0;

        if (resultSet.next()) {
            numero = resultSet.getDouble(columna);
        }

        resultSet.close();
        statement.close();

        return numero;
    }


    public String getLastData(String tableName, String Columna) throws SQLException {
        String sql = "SELECT "+Columna+" FROM "+tableName+" ORDER BY ID_Actualizacion DESC LIMIT 1";
        //System.out.println(sql); TEST
        String ultimoDato = null;
        
        // Ejecutar la consulta y obtener el resultado
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(sql);
        
        // Obtener el último dato de la columna deseada
        if (rs.last()) {
            ultimoDato = rs.getString(Columna);
            //System.out.println("El último dato de la columna es: " + ultimoDato); //TEST
        }
        rs.close();
        stmt.close();
        return ultimoDato;
    }
    }