/**
 * Función Serverless (FaaS) que se activa con Pub/Sub (IE10, IE13)
 */
exports.productoConsumer = (pubSubEvent, context) => {
  const jsonString = Buffer.from(pubSubEvent.data, 'base64').toString();
  
  try {
    const producto = JSON.parse(jsonString);

    console.log('----------------------------------------------------');
    console.log('✅ Función Serverless (FaaS) activada con éxito (IE10)');
    console.log('Producto ID:', producto.id);
    console.log('Producto Nombre:', producto.nombre);
    console.log('Procesando lógica asíncrona...');
    console.log('----------------------------------------------------');

  } catch (error) {
    console.error('Error al parsear el mensaje JSON:', error.message);
  }
};
