package com.neon.sve.model.ventas;

public class Tipos {

    public enum TipoDescuentoCupon {
        PORCENTAJE,
        MONTO
    }

    public enum EstadoReclamo {
        PENDIENTE,
        APROBADO,
        RESUELTO,
        RECHAZADO
    }
}
