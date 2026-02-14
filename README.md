##Patrón de Diseño##
De Comportamiento

##Patrón Utilizado##
Chain of Responsibility (Cadena de Responsabilidad)

##Justificación##
Este patrón es el adecuado porque permite pasar una solicitud (el ticket) a lo largo de una cadena de manejadores potenciales. Al recibir un ticket, cada técnico decide si puede procesarlo basándose en su especialidad y prioridad máxima; si no puede, lo delega al siguiente. Esto evita el acoplamiento directo entre el emisor del ticket y el técnico específico que lo resuelve, permitiendo que el sistema decida dinámicamente el flujo de trabajo.

##Cómo lo aplico##
Handler Abstracto: Se definio SoporteHandler con un método procesar() que gestiona la lógica de pasar el ticket al "siguiente" en la cadena si las condiciones no se cumplen.

Manejadores Concretos: Se creo clases para cada nivel de técnico (Basico, Intermedio, Avanzado), donde cada uno define sus propios límites de resolución.

Configuración de Cadena: En la clase principal, se conecto los objetos de modo que si el básico falla, el ticket sube al intermedio, y de ahí al avanzado.

Streams: Se utilizo Java Streams para procesar toda la lista de tickets de forma funcional y para calcular rápidamente cuántos tickets fueron escalados o resueltos.
