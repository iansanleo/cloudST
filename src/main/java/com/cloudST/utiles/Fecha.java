package com.cloudST.utiles;

import java.sql.Timestamp;
import java.util.Date;

public class Fecha {

	public Date fechaActual(){
		Date fecha = new java.util.Date(); //fecha actual
		Timestamp sqlTimestamp = new Timestamp(fecha.getTime());//en milisegundos
		fecha = new Date(sqlTimestamp.getTime());
		
		return fecha;
	}
}
