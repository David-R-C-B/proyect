package proyect;

/**
 *
 * @author hp
 */
public class CaloriasOptimas {

    // Calorías por porción de los tres alimentos
    private double a;
    private double b;
    private double c;

    // Valor objetivo para el promedio de calorías
    private double valorObjetivo;

    public CaloriasOptimas(double a, double b, double c, double valorObjetivo) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.valorObjetivo = valorObjetivo;
    }

    // Función objetivo: diferencia al cuadrado entre el promedio de calorías y el valor objetivo
    public double funcionObjetivo(double x, double y, double z) {
        double totalCalorias = a * x + b * y + c * z;
        double promedioCalorias = totalCalorias / (x + y + z);
        return Math.pow(promedioCalorias - valorObjetivo, 2);
    }

    // Gradiente de la función objetivo
    public double[] gradiente(double x, double y, double z) {
        double totalCalorias = a * x + b * y + c * z;
        double totalPorciones = x + y + z;
        double promedioCalorias = totalCalorias / totalPorciones;
        double derivadaX = 2 * (promedioCalorias - valorObjetivo) * (a / totalPorciones - totalCalorias / Math.pow(totalPorciones, 2));
        double derivadaY = 2 * (promedioCalorias - valorObjetivo) * (b / totalPorciones - totalCalorias / Math.pow(totalPorciones, 2));
        double derivadaZ = 2 * (promedioCalorias - valorObjetivo) * (c / totalPorciones - totalCalorias / Math.pow(totalPorciones, 2));
        return new double[]{derivadaX, derivadaY, derivadaZ};
    }

    // Método de Newton-Raphson para encontrar las cantidades óptimas de cada alimento
    public double[] newtonRaphson(double x0, double y0, double z0, int maxIteraciones, double tolerancia) {
        double x = x0;
        double y = y0;
        double z = z0;
        for (int i = 0; i < maxIteraciones; i++) {
            double[] grad = gradiente(x, y, z);
            x -= grad[0];
            y -= grad[1];
            z -= grad[2];
            if (Math.sqrt(grad[0] * grad[0] + grad[1] * grad[1] + grad[2] * grad[2]) < tolerancia) {
                break;
            }
        }
        return new double[]{x, y, z};
    }

}

