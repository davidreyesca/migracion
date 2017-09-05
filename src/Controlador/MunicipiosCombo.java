package Controlador;

public class MunicipiosCombo{
    
    public MunicipiosCombo()
    {
    }
    
    String estadoSeleccionado;
    String[] Defautl={};
    String[] Aguascalientes ={"-Seleccionar-", "Aguascalientes", "Asientos", "Calvillo", "Cosio", "El Llano", "Jesus Maria", "Pabellon de Arteaga", "Rincon de Romos",
                            "San Francisco de los Romo", "San Jose de Gracia", "Tepezala"};
    String[] SanLuisPotosi = {"-Seleccionar-", "San Luis Potosí", "Ahualulco", "Alaquines", "Aquismon","Armadillo de los Infante", "Axtla de Terrazas", "Cardenas", "Catorce", "Cedral", "Cerritos", "Cerro de San Pedro", "Charcas", "Ciudad del Maiz", "Ciudad Fernandez", "Ciudad Valles", "Coxcatlan", "Ebano", "El Naranjo", "Guadalcazar", "Huehuetlan", "Lagunillas", "Matehuala", "Matlapa", "Mexquitic de Carmona", "Moctezuma", "Rayon", "Rioverde", "Salinas", "San Antonio", "San Ciro de Acosta", "San Martin Chalchicuautla", 
                            "San Nicolas Tolentino", "San Vicente Tancuayalab", "Santa Catarina", "Santa Maria del Rio", "Santo Domingo", "Soledad de Graciano Sanchez", "Tamasopo", "Tamazunchale", "Tampacan", "Tampamolon Corona", "Tamuin", "Tancanhuitz", "Tanlajas",
                            "Tanquian de Escobedo", "Tierra Nueva", "Vanegas", "Venado", "Villa de Arista", "Villa de Arriaga", "Villa de Guadalupe", "Villa de la Paz", "Villa de Ramos", "Villa de Reyes", "Villa Hidalgo", "Villa Juarez", "Xilitla", "Zaragoza"};

    String[] BajaCalifornia ={"-Seleccionar-", "Ensenada", "Mexicali", "Playas de Rosarito", "Tecate", "Tijuana"};
    String[] BajaCaliforniaSur ={"-Seleccionar-", "Comondu", "La Paz", "Loreto", "Los Cabos","Mulege"};
    String[] Campeche={"-Seleccionar-", "Calkini", "Campeche", "Carmen", "Champoton", "Hecelchakan", "Hopelchen", "Palizada", "Tenabo", "Escarcega", "Calakmul", "Candelaria"};
    String[] Coahuila={"-Seleccionar-", "Abasolo", "Acu", "Allende", "Arteaga", "Candela", "Casta", "Cuatro Cienegas", "Escobedo", "Francisco I. Madero", "Frontera", "General Cepeda", "Guerrero", 
            "Hidalgo", "Jimenez", "Juarez", "Lamadrid", "Matamoros", "Monclova", "Morelos", "Muzquiz", "Nadadores", "Nava", "Ocampo", "Parras", "Piedras Negras", "Progreso", "Ramos Arizpe", 
            "Sabinas", "Sacramento", "Saltillo", "San Buenaventura", "San Juan de Sabinas", "San Pedro", "Sierra Mojada", "Torreon", "Viesca", "Villa Union", "Zaragoza"};
    String[] Colima={"-Seleccionar-", "Armeria", "Colima", "Comala", "Coquimatlan", "Cuauhtemoc", "Ixtlahuacan", "Manzanillo", "Minatitlan", "Tecoman", "Villa de Alvarez"};
    String[] Chiapas={"-Seleccionar-", "Acacoyagua", "Acala", "Acapetahua", "Altamirano", "Amatan", "Amatenango de la Frontera", "Amatenango del Valle", "Angel Albino Corzo", 
            "Arriaga", "Bejucal de Ocampo", "Bella Vista", "Berriozabal", "Bochil", "El Bosque", "Cacahoatan", "Catazaja", "Cintalapa", "Coapilla", "Comitan de Dominguez", "La Concordia", 
            "Copainala", "Chalchihuitan", "Chamula", "Chanal", "Chapultenango", "Chenalho", "Chiapa de Corzo", "Chiapilla", "Chicoasen", "Chicomuselo", "Chilon", "Escuintla", "Francisco Leon", 
            "Frontera Comalapa", "Frontera Hidalgo", "La Grandeza", "Huehuetan", "Huixtan", "Huitiupan", "Huixtla", "La Independencia", "Ixhuatan", "Ixtacomitan", "Ixtapa", "Ixtapangajoya", "Jiquipilas", 
            "Jitotol", "Juarez", "Larrainzar", "La Libertad", "Mapastepec", "Las Margaritas", "Mazapa de Madero", "Mazatan", "Metapa", "Mitontic", "Motozintla", "Nicolas Ruiz", "Ocosingo", "Ocotepec", 
            "Ocozocoautla de Espinosa", "Ostuacan", "Osumacinta", "Oxchuc", "Palenque", "Pantelho", "Pantepec", "Pichucalco", "Pijijiapan", "El Porvenir", "Villa Comaltitlan", "Pueblo Nuevo Solistahuacan", 
            "Rayon", "Reforma", "Las Rosas", "Sabanilla", "Salto de Agua", "San Cristobal de las Casas", "San Fernando", "Siltepec", "Simojovel", "Sitala", "Socoltenango", "Solosuchiapa", "Soyalo", 
            "Suchiapa", "Suchiate", "Sunuapa", "Tapachula", "Tapalapa", "Tapilula", "Tecpatan", "Tenejapa", "Teopisca", "Tila", "Tonala", "Totolapa", "La Trinitaria", "Tumbala", "Tuxtla Gutierrez", 
            "Tuxtla Chico", "Tuzantan", "Tzimol", "Union Juarez", "Venustiano Carranza", "Villa Corzo", "Villaflores", "Yajalon", "San Lucas", "Zinacantan", "San Juan Cancuc", "Aldama", "Benemerito de las Americas", 
            "Maravilla Tenejapa", "Marques de Comillas", "Montecristo de Guerrero", "San Andres Duraznal", "Santiago el Pinar"};
    String[] Chihuahua={"-Seleccionar-", "Ahumada", "Aldama", "Allende", "Aquiles Serdan", "Ascension", "Bachiniva", "Balleza", "Batopilas", "Bocoyna", "Buenaventura", "Camargo", "Carichi", "Casas Grandes", 
            "Chihuahua", "Chinipas", "Coronado", "Coyame del Sotol", "Cuauhtemoc", "Cusihuiriachi", "Delicias", "Dr. Belisario Dominguez", "El Tule", "Galeana", "Gomez Farias", "Gran Morelos", "Guachochi", "Guadalupe y Calvo", 
            "Guadalupe", "Guazapares", "Guerrero", "Hidalgo del Parral", "Huejotitan", "Ignacio Zaragoza", "Janos", "Jimenez", "Juarez", "Julimes", "La Cruz", "Lopez", "Madera", "Maguarichi", "Manuel Benavides", "Matachi", 
            "Matamoros", "Meoqui", "Morelos", "Moris", "Namiquipa", "Nonoava", "Nuevo Casas Grandes", "Ocampo", "Ojinaga", "Praxedis G. Guerrero", "Riva Palacio", "Rosales", "Rosario", "San Francisco de Borja", "San Francisco de Conchos", 
            "San Francisco del Oro", "Santa Barbara", "Santa Isabel", "Satevo", "Saucillo", "Temosachic", "Urique", "Uruachi", "Valle de Zaragoza"};
    String[] DF={"-Seleccionar-", "Alvaro Obregon", "Azcapotzalco", "Benito Juarez", "Coyoacan", "Cuajimalpa de Morelos", "Cuauhtemoc", "Gustavo A. Madero", "Iztacalco", "Iztapalapa", "La Magdalena Contreras", "Miguel Hidalgo", 
            "Milpa Alta", "Tlahuac", "Tlalpan", "Venustiano Carranza", "Xochimilco"};
    String[] Durango={"-Seleccionar-", "Canatlan", "Canelas", "Coneto de Comonfort", "Cuencame", "Durango", "El Oro", "General Simon Bolivar", "Gomez Palacio", "Guadalupe Victoria", "Guanacevi", "Hidalgo", "Inde", "Lerdo", "Mapimi", 
            "Mezquital", "Nazas", "Nombre de Dios", "Nuevo Ideal", "Ocampo", "Otaez", "Panuco de Coronado", "Pe", "Poanas", "Pueblo Nuevo", "Rodeo", "San Bernardo", "San Dimas", "San Juan de Guadalupe", "San Juan del Rio", 
            "San Luis del Cordero", "San Pedro del Gallo", "Santa Clara", "Santiago Papasquiaro", "Suchil", "Tamazula", "Tepehuanes", "Tlahualilo", "Topia", "Vicente Guerrero"};
    String[] Guanajuato={"-Seleccionar-", "Abasolo", "Acambaro", "Apaseo el Alto", "Apaseo el Grande", "Atarjea", "Celaya", "Comonfort", "Coroneo", "Cortazar", "Cueramaro", "Doctor Mora", "Dolores Hidalgo Cuna de la Independencia Nacional", 
            "Guanajuato", "Huanimaro", "Irapuato", "Jaral del Progreso", "Jerecuaro", "Leon", "Manuel Doblado", "Moroleon", "Ocampo", "Penjamo", "Pueblo Nuevo", "Purisima del Rincon", "Romita", "Salamanca", "Salvatierra", "San Diego de la Union", 
            "San Felipe", "San Francisco del Rincon", "San Jose Iturbide", "San Luis de la Paz", "San Miguel de Allende", "Santa Catarina", "Santa Cruz de Juventino Rosas", "Santiago Maravatio", "Silao", "Tarandacuao", "Tarimoro", "Tierra Blanca", 
            "Uriangato", "Valle de Santiago", "Victoria", "Villagran", "Xichu", "Yuriria"};
    String[] Guerrero={"-Seleccionar-", "Acapulco de Juarez", "Acatepec", "Ahuacuotzingo", "Ajuchitlan del Progreso", "Alcozauca de Guerrero", "Alpoyeca", "Apaxtla", "Arcelia", "Atenango del Rio", "Atlamajalcingo del Monte", 
            "Atlixtac", "Atoyac de Alvarez", "Ayutla de los Libres", "Azoyu", "Benito Juarez", "Buenavista de Cuellar", "Chilapa de Alvarez", "Chilpancingo de los Bravo", "Coahuayutla de Jose Maria Izazaga", "Cochoapa el Grande", 
            "Cocula", "Copala", "Copalillo", "Copanatoyac", "Coyuca de Benitez", "Coyuca de Catalan", "Cuajinicuilapa", "Cualac", "Cuautepec", "Cuetzala del Progreso", "Cutzamala de Pinzon", "Eduardo Neri", "Florencio Villarreal", 
            "General Canuto A. Neri", "General Heliodoro Castillo", "Huamuxtitlan", "Huitzuco de los Figueroa", "Iguala de la Independencia", "Igualapa", "Iliatenco", "Ixcateopan de Cuauhtemoc", "Jose Joaquin de Herrera", "Juan R. Escudero", 
            "Juchitan", "La Union de Isidoro Montes de Oca", "Leonardo Bravo", "Malinaltepec", "Marquelia", "Martir de Cuilapan", "Metlatonoc", "Mochitlan", "Olinala", "Ometepec", "Pedro Ascencio Alquisiras", "Petatlan", 
            "Pilcaya", "Pungarabato", "Quechultenango", "San Luis Acatlan", "San Marcos", "San Miguel Totolapan", "Taxco de Alarcon", "Tecoanapa", "Tecpan de Galeana", "Teloloapan", "Tepecoacuilco de Trujano", "Tetipac", "Tixtla de Guerrero", 
            "Tlacoachistlahuaca", "Tlacoapa", "Tlalchapa", "Tlalixtaquilla de Maldonado", "Tlapa de Comonfort", "Tlapehuala", "Xalpatlahuac", "Xochihuehuetlan", "Xochistlahuaca", "Zapotitlan Tablas", "Zihuatanejo de Azueta", "Zirandaro", "Zitlala"};
    String[] Hidalgo={"-Seleccionar-", "Acatlan", "Acaxochitlan", "Actopan", "Agua Blanca de Iturbide", "Ajacuba", "Alfajayucan", "Almoloya", "Apan", "Atitalaquia", "Atlapexco", "Atotonilco de Tula", "Atotonilco el Grande", "Calnali", "Cardonal", "Chapantongo", 
            "Chapulhuacan", "Chilcuautla", "Cuautepec de Hinojosa", "El Arenal", "Eloxochitlan", "Emiliano Zapata", "Epazoyucan", "Francisco I. Madero", "Huasca de Ocampo", "Huautla", "Huazalingo", "Huehuetla", "Huejutla de Reyes", "Huichapan", 
            "Ixmiquilpan", "Jacala de Ledezma", "Jaltocan", "Juarez Hidalgo", "La Mision", "Lolotla", "Metepec", "Metztitlan", "Mineral de la Reforma", "Mineral del Chico", "Mineral del Monte", "Mixquiahuala de Juarez", "Molango de Escamilla", "Nicolas Flores", 
            "Nopala de Villagran", "Omitlan de Juarez", "Pachuca de Soto", "Pacula", "Pisaflores", "Progreso de Obregon", "San Agustin Metzquititlan", "San Agustin Tlaxiaca", "San Bartolo Tutotepec", "San Felipe Orizatlan", "San Salvador", "Santiago de Anaya", 
            "Santiago Tulantepec de Lugo Guerrero", "Singuilucan", "Tasquillo", "Tecozautla", "Tenango de Doria", "Tepeapulco", "Tepehuacan de Guerrero", "Tepeji del Rio de Ocampo", "Tepetitlan", "Tetepango", "Tezontepec de Aldama", "Tianguistengo", 
            "Tizayuca", "Tlahuelilpan", "Tlahuiltepa", "Tlanalapa", "Tlanchinol", "Tlaxcoapan", "Tolcayuca", "Tula de Allende", "Tulancingo de Bravo", "Villa de Tezontepec", "Xochiatipan", "Xochicoatlan", "Yahualica", "Zacualtipan de Angeles", 
            "Zapotlan de Juarez", "Zempoala", "Zimapan"};
    String[] Jalisco={"-Seleccionar-", "Acatic", "Acatlan de Juarez", "Ahualulco de Mercado", "Amacueca", "Amatitan", "Ameca", "Arandas", "Atemajac de Brizuela", "Atengo", "Atenguillo", "Atotonilco el Alto", "Atoyac", "Autlan de Navarro", "Ayotlan", 
            "Ayutla", "Bola", "Ca", "Cabo Corrientes", "Casimiro Castillo", "Chapala", "Chimaltitan", "Chiquilistlan", "Cihuatlan", "Cocula", "Colotlan", "Concepcion de Buenos Aires", "Cuautitlan de Garcia Barragan", "Cuautla", "Cuquio", "Degollado", "Ejutla", 
            "El Arenal", "El Grullo", "El Limon", "El Salto", "Encarnacion de Diaz", "Etzatlan", "Gomez Farias", "Guachinango", "Guadalajara", "Hostotipaquillo", "Huejucar", "Huejuquilla el Alto", "Ixtlahuacan de los Membrillos", "Ixtlahuacan del Rio", "Jalostotitlan", 
            "Jamay", "Jesus Maria", "Jilotlan de los Dolores", "Jocotepec", "Juanacatlan", "Juchitlan", "La Barca", "La Huerta", "La Manzanilla de la Paz", "Lagos de Moreno", "Magdalena", "Mascota", "Mazamitla", "Mexticacan", "Mezquitic", "Mixtlan", "Ocotlan", "Ojuelos de Jalisco", 
            "Pihuamo", "Poncitlan", "Puerto Vallarta", "Quitupan", "San Cristobal de la Barranca", "San Diego de Alejandria", "San Gabriel", "San Ignacio Cerro Gordo", "San Juan de los Lagos", "San Juanito de Escobedo", "San Julian", "San Marcos", "San Martin de Bola", 
            "San Martin Hidalgo", "San Miguel el Alto", "San Pedro Tlaquepaque", "San Sebastian del Oeste", "Santa Maria de los Angeles", "Santa Maria del Oro", "Sayula", "Tala", "Talpa de Allende", "Tamazula de Gordiano", "Tapalpa", "Tecalitlan", "Techaluta de Montenegro", 
            "Tecolotlan", "Tenamaxtlan", "Teocaltiche", "Teocuitatlan de Corona", "Tepatitlan de Morelos", "Tequila", "Teuchitlan", "Tizapan el Alto", "Tlajomulco de Zu", "Toliman", "Tomatlan", "Tonala", "Tonaya", "Tonila", "Totatiche", "Tototlan", "Tuxcacuesco", 
            "Tuxcueca", "Tuxpan", "Union de San Antonio", "Union de Tula", "Valle de Guadalupe", "Valle de Juarez", "Villa Corona", "Villa Guerrero", "Villa Hidalgo", "Villa Purificacion", "Yahualica de Gonzalez Gallo", "Zacoalco de Torres", "Zapopan", "Zapotiltic", 
            "Zapotitlan de Vadillo", "Zapotlan del Rey", "Zapotlan el Grande", "Zapotlanejo"};
    String[] Mexico={"-Seleccionar-", "Acambay", "Acolman", "Aculco", "Almoloya de Alquisiras", "Almoloya de Juarez", "Almoloya del Rio", "Amanalco", "Amatepec", "Amecameca", "Apaxco", "Atenco", "Atizapan de Zaragoza", "Atizapan", "Atlacomulco", "Atlautla", "Axapusco", "Ayapango", 
            "Calimaya", "Capulhuac", "Chalco", "Chapa de Mota", "Chapultepec", "Chiautla", "Chicoloapan", "Chiconcuac", "Chimalhuacan", "Coacalco de Berriozabal", "Coatepec Harinas", "Cocotitlan", "Coyotepec", "Cuautitlan Izcalli", "Cuautitlan", "Donato Guerra", "Ecatepec de Morelos", 
            "Ecatzingo", "El Oro", "Huehuetoca", "Hueypoxtla", "Huixquilucan", "Isidro Fabela", "Ixtapaluca", "Ixtapan de la Sal", "Ixtapan del Oro", "Ixtlahuaca", "Jaltenco", "Jilotepec", "Jilotzingo", "Jiquipilco", "Jocotitlan", "Joquicingo", "Juchitepec", "La Paz", "Lerma", 
            "Luvianos", "Malinalco", "Melchor Ocampo", "Metepec", "Mexicaltzingo", "Morelos", "Naucalpan de Juarez", "Nextlalpan", "Nezahualcoyotl", "Nicolas Romero", "Nopaltepec", "Ocoyoacac", "Ocuilan", "Otumba", "Otzoloapan", "Otzolotepec", "Ozumba", "Papalotla", "Polotitlan", 
            "Rayon", "San Antonio la Isla", "San Felipe del Progreso", "San Jose del Rincon", "San Martin de las Piramides", "San Mateo Atenco", "San Simon de Guerrero", "Santo Tomas", "Soyaniquilpan de Juarez", "Sultepec", "Tecamac", "Tejupilco", "Temamatla", "Temascalapa", "Temascalcingo", 
            "Temascaltepec", "Temoaya", "Tenancingo", "Tenango del Aire", "Tenango del Valle", "Teoloyucan", "Teotihuacan", "Tepetlaoxtoc", "Tepetlixpa", "Tepotzotlan", "Tequixquiac", "Texcaltitlan", "Texcalyacac", "Texcoco", "Tezoyuca", "Tianguistenco", "Timilpan", "Tlalmanalco", 
            "Tlalnepantla de Baz", "Tlatlaya", "Toluca", "Tonanitla", "Tonatico", "Tultepec", "Tultitlan", "Valle de Bravo", "Valle de Chalco Solidaridad", "Villa de Allende", "Villa del Carbon", "Villa Guerrero", "Villa Victoria", "Xalatlaco", "Xonacatlan", "Zacazonapan", 
            "Zacualpan", "Zinacantepec", "Zumpahuacan", "Zumpango"};
    String[] Michoacan={"-Seleccionar-", "Acuitzio", "Aguililla", "Alvaro Obregon", "Angamacutiro", "Angangueo", "Apatzingan", "Aporo", "Aquila", "Ario", "Arteaga", "Brise", "Buenavista", "Caracuaro", "Charapan", "Charo", "Chavinda", "Cheran", "Chilchota", "Chinicuila", "Chucandiro", "Churintzio", 
            "Churumuco", "Coahuayana", "Coalcoman de Vazquez Pallares", "Coeneo", "Cojumatlan de Regules", "Contepec", "Copandaro", "Cotija", "Cuitzeo", "Ecuandureo", "Epitacio Huerta", "Erongaricuaro", "Gabriel Zamora", "Hidalgo", "Huandacareo", "Huaniqueo", "Huetamo", "Huiramba", "Indaparapeo", 
            "Irimbo", "Ixtlan", "Jacona", "Jimenez", "Jiquilpan", "Jose Sixto Verduzco", "Juarez", "Jungapeo", "La Huacana", "La Piedad", "Lagunillas", "Lazaro Cardenas", "Los Reyes", "Madero", "Maravatio", "Marcos Castellanos", "Morelia", "Morelos", "Mugica", "Nahuatzen", "Nocupetaro", "Nuevo Parangaricutiro", 
            "Nuevo Urecho", "Numaran", "Ocampo", "Pajacuaran", "Panindicuaro", "Paracho", "Paracuaro", "Patzcuaro", "Penjamillo", "Periban", "Purepero", "Puruandiro", "Querendaro", "Quiroga", "Sahuayo", "Salvador Escalante", "San Lucas", "Santa Ana Maya", "Senguio", "Susupuato", "Tacambaro", 
            "Tancitaro", "Tangamandapio", "Tangancicuaro", "Tanhuato", "Taretan", "Tarimbaro", "Tepalcatepec", "Ting", "Tingambato", "Tiquicheo de Nicolas Romero", "Tlalpujahua", "Tlazazalca", "Tocumbo", "Tumbiscatio", "Turicato", "Tuxpan", "Tuzantla", "Tzintzuntzan", "Tzitzio", "Uruapan", 
            "Venustiano Carranza", "Villamar", "Vista Hermosa", "Yurecuaro", "Zacapu", "Zamora", "Zinaparo", "Zinapecuaro", "Ziracuaretiro", "Zitacuaro"};
    String[] Morelos={"-Seleccionar-", "Amacuzac", "Atlatlahucan", "Axochiapan", "Ayala", "Coatlan del Rio", "Cuautla", "Cuernavaca", "Emiliano Zapata", "Huitzilac", "Jantetelco", "Jiutepec", "Jojutla", "Jonacatepec", "Mazatepec", "Miacatlan", "Ocuituco", "Puente de Ixtla", "Temixco", "Temoac", 
            "Tepalcingo", "Tepoztlan", "Tetecala", "Tetela del Volcan", "Tlalnepantla", "Tlaltizapan de Zapata", "Tlaquiltenango", "Tlayacapan", "Totolapan", "Xochitepec", "Yautepec", "Yecapixtla", "Zacatepec", "Zacualpan"};
    String[] Nayarit={"-Seleccionar-", "Acaponeta", "Ahuacatlan", "Amatlan de Ca", "Bahia de Banderas", "Compostela", "Del Nayar", "Huajicori", "Ixtlan del Rio", "Jala", "La Yesca", "Rosamorada", "Ruiz", "San Blas", "San Pedro Lagunillas", "Santa Maria del Oro", "Santiago Ixcuintla", 
            "Tecuala", "Tepic", "Tuxpan", "Xalisco"};
    String[] NuevoLeon={"-Seleccionar-", "Abasolo", "Agualeguas", "Allende", "Anahuac", "Apodaca", "Aramberri", "Bustamante", "Cadereyta Jimenez", "Carmen", "Cerralvo", "China", "Cienega de Flores", "Dr. Arroyo", "Dr. Coss", "Dr. Gonzalez", "Galeana", "Garcia", "Gral. Bravo", 
            "Gral. Escobedo", "Gral. Teran", "Gral. Trevi", "Gral. Zaragoza", "Gral. Zuazua", "Guadalupe", "Hidalgo", "Higueras", "Hualahuises", "Iturbide", "Juarez", "Lampazos de Naranjo", "Linares", "Los Aldamas", "Los Herreras", "Los Ramones", "Marin", "Melchor Ocampo", "Mier y Noriega", "Mina", 
            "Montemorelos", "Monterrey", "Paras", "Pesqueria", "Rayones", "Sabinas Hidalgo", "Salinas Victoria", "San Nicolas de los Garza", "San Pedro Garza Garcia", "Santa Catarina", "Santiago", "Vallecillo", "Villaldama"};
    String[] Oaxaca={"-Seleccionar-", "Abejones", "Acatlan de Perez Figueroa", "Animas Trujano", "Asuncion Cacalotepec", "Asuncion Cuyotepeji", "Asuncion Ixtaltepec", "Asuncion Nochixtlan", "Asuncion Ocotlan", "Asuncion Tlacolulita", "Ayoquezco de Aldama", "Ayotzintepec", "Calihuala", "Candelaria Loxicha", 
            "Capulalpam de Mendez", "Chahuites", "Chalcatongo de Hidalgo", "Chiquihuitlan de Benito Juarez", "Cienega de Zimatlan", "Ciudad Ixtepec", "Coatecas Altas", "Coicoyan de las Flores", "Concepcion Buenavista", "Concepcion Papalo", "Constancia del Rosario", "Cosolapa", "Cosoltepec", 
            "Cuilapam de Guerrero", "Cuyamecalco Villa de Zaragoza", "El Barrio de la Soledad", "El Espinal", "Eloxochitlan de Flores Magon", "Fresnillo de Trujano", "Guadalupe de Ramirez", "Guadalupe Etla", "Guelatao de Juarez", "Guevea de Humboldt", "Heroica Ciudad de Ejutla de Crespo", "Heroica Ciudad de Huajuapan de Leon", 
            "Heroica Ciudad de Juchitan de Zaragoza", "Heroica Ciudad de Tlaxiaco", "Heroica Villa Tezoatlan de Segura y Luna, Cuna...", "Huautepec", "Huautla de Jimenez", "Ixpantepec Nieves", "Ixtlan de Juarez", "La Compa", "La Pe", "La Reforma", "La Trinidad Vista Hermosa", "Loma Bonita", 
            "Magdalena Apasco", "Magdalena Jaltepec", "Magdalena Mixtepec", "Magdalena Ocotlan", "Magdalena Pe", "Magdalena Teitipac", "Magdalena Tequisistlan", "Magdalena Tlacotepec", "Magdalena Yodocono de Porfirio Diaz","Magdalena Zahuatlan", "Mariscala de Juarez", "Martires de Tacubaya", "Matias Romero Avenda", 
            "Mazatlan Villa de Flores", "Mesones Hidalgo", "Miahuatlan de Porfirio Diaz", "Mixistlan de la Reforma", "Monjas", "Natividad", "Nazareno Etla", "Nejapa de Madero", "Nuevo Zoquiapam", "Oaxaca de Juarez", "Ocotlan de Morelos", "Pinotepa de Don Luis", "Pluma Hidalgo", "Putla Villa de Guerrero", "Reforma de Pineda", 
            "Reyes Etla", "Rojas de Cuauhtemoc", "Salina Cruz", "San Agustin Amatengo", "San Agustin Atenango", "San Agustin Chayuco", "San Agustin de las Juntas", "San Agustin Etla", "San Agustin Loxicha", "San Agustin Tlacotepec", "San Agustin Yatareni", "San Andres Cabecera Nueva", "San Andres Dinicuiti", 
            "San Andres Huaxpaltepec", "San Andres Huayapam", "San Andres Ixtlahuaca", "San Andres Lagunas", "San Andres Nuxi", "San Andres Paxtlan", "San Andres Sinaxtla", "San Andres Solaga", "San Andres Teotilalpam", "San Andres Tepetlapa", "San Andres Yaa", "San Andres Zabache", "San Andres Zautla", "San Antonino Castillo Velasco", 
            "San Antonino el Alto", "San Antonino Monte Verde", "San Antonio Acutla", "San Antonio de la Cal", "San Antonio Huitepec", "San Antonio Nanahuatipam", "San Antonio Sinicahua", "San Antonio Tepetlapa", "San Baltazar Chichicapam", "San Baltazar Loxicha", "San Baltazar Yatzachi el Bajo", "San Bartolo Coyotepec", 
            "San Bartolo Soyaltepec", "San Bartolo Yautepec", "San Bartolome Ayautla", "San Bartolome Loxicha", "San Bartolome Quialana", "San Bartolome Yucua", "San Bartolome Zoogocho", "San Bernardo Mixtepec", "San Blas Atempa", "San Carlos Yautepec", "San Cristobal Amatlan", "San Cristobal Amoltepec", "San Cristobal Lachirioag", 
            "San Cristobal Suchixtlahuaca", "San Dionisio del Mar", "San Dionisio Ocotepec", "San Dionisio Ocotlan", "San Esteban Atatlahuca", "San Felipe Jalapa de Diaz", "San Felipe Tejalapam", "San Felipe Usila", "San Francisco Cahuacua", "San Francisco Cajonos", "San Francisco Chapulapa", 
            "San Francisco Chindua", "San Francisco del Mar", "San Francisco Huehuetlan", "San Francisco Ixhuatan", "San Francisco Jaltepetongo", "San Francisco Lachigolo", "San Francisco Logueche", "San Francisco Nuxa", "San Francisco Ozolotepec", "San Francisco Sola", "San Francisco Telixtlahuaca", "San Francisco Teopan", 
            "San Francisco Tlapancingo", "San Gabriel Mixtepec", "San Ildefonso Amatlan", "San Ildefonso Sola", "San Ildefonso Villa Alta", "San Jacinto Amilpas", "San Jacinto Tlacotepec", "San Jeronimo Coatlan", "San Jeronimo Silacayoapilla", "San Jeronimo Sosola", "San Jeronimo Taviche", "San Jeronimo Tecoatl", "San Jeronimo Tlacochahuaya", 
            "San Jorge Nuchita", "San Jose Ayuquila", "San Jose Chiltepec", "San Jose del Pe", "San Jose del Progreso", "San Jose Estancia Grande", "San Jose Independencia", "San Jose Lachiguiri", "San Jose Tenango", "San Juan ", "San Juan Achiutla", "San Juan Atepec", "San Juan Bautista Atatlahuca", 
            "San Juan Bautista Coixtlahuaca", "San Juan Bautista Cuicatlan", "San Juan Bautista Guelache", "San Juan Bautista Jayacatlan", "San Juan Bautista Lo de Soto", "San Juan Bautista Suchitepec", "San Juan Bautista Tlachichilco", "San Juan Bautista Tlacoatzintepec", "San Juan Bautista Tuxtepec","San Juan Bautista Valle Nacional", 
            "San Juan Cacahuatepec", "San Juan Chicomezuchil", "San Juan Chilateca", "San Juan Cieneguilla", "San Juan Coatzospam", "San Juan Colorado", "San Juan Comaltepec", "San Juan Cotzocon", "San Juan de los Cues", "San Juan del Estado", "San Juan del Rio", "San Juan Diuxi", "San Juan Evangelista Analco", 
            "San Juan Guelavia", "San Juan Guichicovi", "San Juan Ihualtepec", "San Juan Juquila Mixes", "San Juan Juquila Vijanos", "San Juan Lachao", "San Juan Lachigalla", "San Juan Lajarcia", "San Juan Lalana", "San Juan Mazatlan", "San Juan Mixtepec -Dto. 08 -", "San Juan Mixtepec -Dto. 26 -", "San Juan Ozolotepec", 
            "San Juan Petlapa", "San Juan Quiahije", "San Juan Quiotepec", "San Juan Sayultepec", "San Juan Tabaa", "San Juan Tamazola", "San Juan Teita", "San Juan Teitipac", "San Juan Tepeuxila", "San Juan Teposcolula", "San Juan Yaee", "San Juan Yatzona", "San Juan Yucuita", "San Lorenzo Albarradas", 
            "San Lorenzo Cacaotepec", "San Lorenzo Cuaunecuiltitla", "San Lorenzo Texmelucan", "San Lorenzo Victoria", "San Lorenzo", "San Lucas Camotlan", "San Lucas Ojitlan", "San Lucas Quiavini", "San Lucas Zoquiapam", "San Luis Amatlan", "San Marcial Ozolotepec", "San Marcos Arteaga", "San Martin de los Cansecos", 
            "San Martin Huamelulpam", "San Martin Itunyoso", "San Martin Lachila", "San Martin Peras", "San Martin Tilcajete", "San Martin Toxpalan", "San Martin Zacatepec", "San Mateo Cajonos", "San Mateo del Mar", "San Mateo Etlatongo", "San Mateo Nejapam", "San Mateo Pe", "San Mateo Pi", "San Mateo Rio Hondo", "San Mateo Sindihui", 
            "San Mateo Tlapiltepec", "San Mateo Yoloxochitlan", "San Mateo Yucutindo", "San Melchor Betaza", "San Miguel Achiutla", "San Miguel Ahuehuetitlan", "San Miguel Aloapam", "San Miguel Amatitlan", "San Miguel Amatlan", "San Miguel Chicahua", "San Miguel Chimalapa", "San Miguel Coatlan", "San Miguel del Puerto", 
            "San Miguel del Rio", "San Miguel Ejutla", "San Miguel el Grande", "San Miguel Huautla", "San Miguel Mixtepec", "San Miguel Panixtlahuaca", "San Miguel Peras", "San Miguel Piedras", "San Miguel Quetzaltepec", "San Miguel Santa Flor", "San Miguel Soyaltepec", "San Miguel Suchixtepec", "San Miguel Tecomatlan", 
            "San Miguel Tenango", "San Miguel Tequixtepec", "San Miguel Tilquiapam", "San Miguel Tlacamama", "San Miguel Tlacotepec", "San Miguel Tulancingo", "San Miguel Yotao", "San Nicolas Hidalgo", "San Nicolas", "San Pablo Coatlan", "San Pablo Cuatro Venados", "San Pablo Etla", "San Pablo Huitzo", "San Pablo Huixtepec", 
            "San Pablo Macuiltianguis", "San Pablo Tijaltepec", "San Pablo Villa de Mitla", "San Pablo Yaganiza", "San Pedro Amuzgos", "San Pedro Apostol", "San Pedro Atoyac", "San Pedro Cajonos", "San Pedro Comitancillo", "San Pedro Coxcaltepec Cantaros", "San Pedro el Alto", 
            "San Pedro Huamelula", "San Pedro Huilotepec", "San Pedro Ixcatlan", "San Pedro Ixtlahuaca", "San Pedro Jaltepetongo", "San Pedro Jicayan", "San Pedro Jocotipac", "San Pedro Juchatengo", "San Pedro Martir Quiechapa", "San Pedro Martir Yucuxaco", "San Pedro Martir", "San Pedro Mixtepec -Dto. 22 -", 
            "San Pedro Mixtepec -Dto. 26 -", "San Pedro Molinos", "San Pedro Nopala", "San Pedro Ocopetatillo", "San Pedro Ocotepec", "San Pedro Pochutla", "San Pedro Quiatoni", "San Pedro Sochiapam", "San Pedro Tapanatepec", "San Pedro Taviche", "San Pedro Teozacoalco", "San Pedro Teutila", "San Pedro Tidaa", 
            "San Pedro Topiltepec", "San Pedro Totolapam", "San Pedro y San Pablo Ayutla", "San Pedro y San Pablo Teposcolula", "San Pedro y San Pablo Tequixtepec", "San Pedro Yaneri", "San Pedro Yolox", "San Pedro Yucunama", "San Raymundo Jalpan", "San Sebastian Abasolo", "San Sebastian Coatlan", "San Sebastian Ixcapa", 
            "San Sebastian Nicananduta", "San Sebastian Rio Hondo", "San Sebastian Tecomaxtlahuaca", "San Sebastian Teitipac", "San Sebastian Tutla", "San Simon Almolongas", "San Simon Zahuatlan", "San Vicente Coatlan", "San Vicente Lachixio", "San Vicente Nu", "Santa Ana Ateixtlahuaca", "Santa Ana Cuauhtemoc", 
            "Santa Ana del Valle", "Santa Ana Tavela", "Santa Ana Tlapacoyan", "Santa Ana Yareni", "Santa Ana Zegache", "Santa Ana", "Santa Catalina Quieri", "Santa Catarina Cuixtla", "Santa Catarina Ixtepeji", "Santa Catarina Juquila", "Santa Catarina Lachatao", "Santa Catarina Loxicha", "Santa Catarina Mechoacan", 
            "Santa Catarina Minas", "Santa Catarina Quiane", "Santa Catarina Quioquitani", "Santa Catarina Tayata", "Santa Catarina Ticua", "Santa Catarina Yosonotu", "Santa Catarina Zapoquila", "Santa Cruz Acatepec", "Santa Cruz Amilpas", "Santa Cruz de Bravo", "Santa Cruz Itundujia", "Santa Cruz Mixtepec", 
            "Santa Cruz Nundaco", "Santa Cruz Papalutla", "Santa Cruz Tacache de Mina", "Santa Cruz Tacahua", "Santa Cruz Tayata", "Santa Cruz Xitla", "Santa Cruz Xoxocotlan", "Santa Cruz Zenzontepec", "Santa Gertrudis", "Santa Ines de Zaragoza", "Santa Ines del Monte", "Santa Ines Yatzeche", "Santa Lucia del Camino", 
            "Santa Lucia Miahuatlan", "Santa Lucia Monteverde", "Santa Lucia Ocotlan", "Santa Magdalena Jicotlan", "Santa Maria Alotepec", "Santa Maria Apazco", "Santa Maria Atzompa", "Santa Maria Camotlan", "Santa Maria Chachoapam", "Santa Maria Chilchotla", "Santa Maria Chimalapa", "Santa Maria Colotepec", 
            "Santa Maria Cortijo", "Santa Maria Coyotepec", "Santa Maria del Rosario", "Santa Maria del Tule", "Santa Maria Ecatepec", "Santa Maria Guelace", "Santa Maria Guienagati", "Santa Maria Huatulco", "Santa Maria Huazolotitlan", "Santa Maria Ipalapa", "Santa Maria Ixcatlan", "Santa Maria Jacatepec", 
            "Santa Maria Jalapa del Marques", "Santa Maria Jaltianguis", "Santa Maria la Asuncion", "Santa Maria Lachixio", "Santa Maria Mixtequilla", "Santa Maria Nativitas", "Santa Maria Nduayaco", "Santa Maria Ozolotepec", "Santa Maria Papalo", "Santa Maria Pe", "Santa Maria Petapa", "Santa Maria Quiegolani", 
            "Santa Maria Sola", "Santa Maria Tataltepec", "Santa Maria Tecomavaca", "Santa Maria Temaxcalapa", "Santa Maria Temaxcaltepec", "Santa Maria Teopoxco", "Santa Maria Tepantlali", "Santa Maria Texcatitlan", "Santa Maria Tlahuitoltepec", "Santa Maria Tlalixtac", "Santa Maria Tonameca", "Santa Maria Totolapilla", 
            "Santa Maria Xadani", "Santa Maria Yalina", "Santa Maria Yavesia", "Santa Maria Yolotepec", "Santa Maria Yosoyua", "Santa Maria Yucuhiti", "Santa Maria Zacatepec", "Santa Maria Zaniza", "Santa Maria Zoquitlan", "Santiago Amoltepec", "Santiago Apoala", "Santiago Apostol", "Santiago Astata", "Santiago Atitlan", 
            "Santiago Ayuquililla", "Santiago Cacaloxtepec", "Santiago Camotlan", "Santiago Chazumba", "Santiago Choapam", "Santiago Comaltepec", "Santiago del Rio", "Santiago Huajolotitlan", "Santiago Huauclilla", "Santiago Ihuitlan Plumas", "Santiago Ixcuintepec", "Santiago Ixtayutla", "Santiago Jamiltepec", 
            "Santiago Jocotepec", "Santiago Juxtlahuaca", "Santiago Lachiguiri", "Santiago Lalopa", "Santiago Laollaga", "Santiago Laxopa", "Santiago Llano Grande", "Santiago Matatlan", "Santiago Miltepec", "Santiago Minas", "Santiago Nacaltepec", "Santiago Nejapilla", "Santiago Niltepec", "Santiago Nundiche", 
            "Santiago Nuyoo", "Santiago Pinotepa Nacional", "Santiago Suchilquitongo", "Santiago Tamazola", "Santiago Tapextla", "Santiago Tenango", "Santiago Tepetlapa", "Santiago Tetepec", "Santiago Texcalcingo", "Santiago Textitlan", "Santiago Tilantongo", "Santiago Tillo", "Santiago Tlazoyaltepec", 
            "Santiago Xanica", "Santiago Xiacui", "Santiago Yaitepec", "Santiago Yaveo", "Santiago Yolomecatl", "Santiago Yosondua", "Santiago Yucuyachi", "Santiago Zacatepec", "Santiago Zoochila", "Santo Domingo Albarradas", "Santo Domingo Armenta", "Santo Domingo Chihuitan", "Santo Domingo de Morelos", 
            "Santo Domingo Ingenio", "Santo Domingo Ixcatlan", "Santo Domingo Nuxaa", "Santo Domingo Ozolotepec", "Santo Domingo Petapa", "Santo Domingo Roayaga", "Santo Domingo Tehuantepec", "Santo Domingo Teojomulco", "Santo Domingo Tepuxtepec", "Santo Domingo Tlatayapam", "Santo Domingo Tomaltepec", 
            "Santo Domingo Tonala", "Santo Domingo Tonaltepec", "Santo Domingo Xagacia", "Santo Domingo Yanhuitlan", "Santo Domingo Yodohino", "Santo Domingo Zanatepec", "Santo Tomas Jalieza", "Santo Tomas Mazaltepec", "Santo Tomas Ocotepec", "Santo Tomas Tamazulapan", "Santos Reyes Nopala", "Santos Reyes Papalo", 
            "Santos Reyes Tepejillo", "Santos Reyes Yucuna", "Silacayoapam", "Sitio de Xitlapehua", "Soledad Etla", "Tamazulapam del Espiritu Santo", "Tanetze de Zaragoza", "Taniche", "Tataltepec de Valdes", "Teococuilco de Marcos Perez", "Teotitlan de Flores Magon", "Teotitlan del Valle", "Teotongo", 
            "Tepelmeme Villa de Morelos", "Tlacolula de Matamoros", "Tlacotepec Plumas", "Tlalixtac de Cabrera", "Totontepec Villa de Morelos", "Trinidad Zaachila", "Union Hidalgo", "Valerio Trujano", "Villa de Chilapa de Diaz", "Villa de Etla", "Villa de Tamazulapam del Progreso", "Villa de Tututepec de Melchor Ocampo", 
            "Villa de Zaachila", "Villa Diaz Ordaz", "Villa Hidalgo", "Villa Sola de Vega", "Villa Talea de Castro", "Villa Tejupam de la Union", "Yaxe", "Yogana", "Yutanduchi de Guerrero", "Zapotitlan Lagunas", "Zapotitlan Palmas", "Zimatlan de Alvarez"};
    String[] Puebla={"-Seleccionar-", "Acajete", "Acateno", "Acatlan", "Acatzingo", "Acteopan", "Ahuacatlan", "Ahuatlan", "Ahuazotepec", "Ahuehuetitla", "Ajalpan", "Albino Zertuche", "Aljojuca", "Altepexi", "Amixtlan", "Amozoc", "Aquixtla", "Atempan", "Atexcal", "Atlequizayan", "Atlixco", "Atoyatempan", "Atzala", "Atzitzihuacan", 
            "Atzitzintla", "Axutla", "Ayotoxco de Guerrero", "Ca", "Calpan", "Caltepec", "Camocuautla", "Caxhuacan", "Chalchicomula de Sesma", "Chapulco", "Chiautla", "Chiautzingo", "Chichiquila", "Chiconcuautla", "Chietla", "Chigmecatitlan", "Chignahuapan", "Chignautla", "Chila de la Sal", "Chila", "Chilchotla", "Chinantla", "Coatepec", 
            "Coatzingo", "Cohetzala", "Cohuecan", "Coronango", "Coxcatlan", "Coyomeapan", "Coyotepec", "Cuapiaxtla de Madero", "Cuautempan", "Cuautinchan", "Cuautlancingo", "Cuayuca de Andrade", "Cuetzalan del Progreso", "Cuyoaco", "Domingo Arenas", "Eloxochitlan", "Epatlan", "Esperanza", "Francisco Z. Mena", "General Felipe Angeles", 
            "Guadalupe Victoria", "Guadalupe", "Hermenegildo Galeana", "Honey", "Huaquechula", "Huatlatlauca", "Huauchinango", "Huehuetla", "Huehuetlan el Chico", "Huehuetlan el Grande", "Huejotzingo", "Hueyapan", "Hueytamalco", "Hueytlalpan", "Huitzilan de Serdan", "Huitziltepec", "Ixcamilpa de Guerrero", "Ixcaquixtla", "Ixtacamaxtitlan", 
            "Ixtepec", "Izucar de Matamoros", "Jalpan", "Jolalpan", "Jonotla", "Jopala", "Juan C. Bonilla", "Juan Galindo", "Juan N. Mendez", "La Magdalena Tlatlauquitepec", "Lafragua", "Libres", "Los Reyes de Juarez", "Mazapiltepec de Juarez", "Mixtla", "Molcaxac", "Naupan", "Nauzontla", "Nealtican", "Nicolas Bravo", "Nopalucan", 
            "Ocotepec", "Ocoyucan", "Olintla", "Oriental", "Pahuatlan", "Palmar de Bravo", "Pantepec", "Petlalcingo", "Piaxtla", "Puebla", "Quecholac", "Quimixtlan", "Rafael Lara Grajales", "San Andres Cholula", "San Antonio Ca", "San Diego la Mesa Tochimiltzingo", "San Felipe Teotlalcingo", "San Felipe Tepatlan", 
            "San Gabriel Chilac", "San Gregorio Atzompa", "San Jeronimo Tecuanipan", "San Jeronimo Xayacatlan", "San Jose Chiapa", "San Jose Miahuatlan", "San Juan Atenco", "San Juan Atzompa", "San Martin Texmelucan", "San Martin Totoltepec", "San Matias Tlalancaleca", "San Miguel Ixitlan", "San Miguel Xoxtla", "San Nicolas Buenos Aires", 
            "San Nicolas de los Ranchos", "San Pablo Anicano", "San Pedro Cholula", "San Pedro Yeloixtlahuaca", "San Salvador el Seco", "San Salvador el Verde", "San Salvador Huixcolotla", "San Sebastian Tlacotepec", "Santa Catarina Tlaltempan", "Santa Ines Ahuatempan", "Santa Isabel Cholula", "Santiago Miahuatlan", 
            "Santo Tomas Hueyotlipan", "Soltepec", "Tecali de Herrera", "Tecamachalco", "Tecomatlan", "Tehuacan", "Tehuitzingo", "Tenampulco", "Teopantlan", "Teotlalco", "Tepanco de Lopez", "Tepango de Rodriguez", "Tepatlaxco de Hidalgo", "Tepeaca", "Tepemaxalco", "Tepeojuma", "Tepetzintla", "Tepexco", "Tepexi de Rodriguez", "Tepeyahualco de Cuauhtemoc", 
            "Tepeyahualco", "Tetela de Ocampo", "Teteles de Avila Castillo", "Teziutlan", "Tianguismanalco", "Tilapa", "Tlachichuca", "Tlacotepec de Benito Juarez", "Tlacuilotepec", "Tlahuapan", "Tlaltenango", "Tlanepantla", "Tlaola", "Tlapacoya", "Tlapanala", "Tlatlauquitepec", "Tlaxco", "Tochimilco", "Tochtepec", "Totoltepec de Guerrero", "Tulcingo", 
            "Tuzamapan de Galeana", "Tzicatlacoyan", "Venustiano Carranza", "Vicente Guerrero", "Xayacatlan de Bravo","Xicotepec", "Xicotlan", "Xiutetelco", "Xochiapulco", "Xochiltepec", "Xochitlan de Vicente Suarez", "Xochitlan Todos Santos", "Yaonahuac", "Yehualtepec", "Zacapala", "Zacapoaxtla", "Zacatlan", "Zapotitlan de Mendez", "Zapotitlan", 
            "Zaragoza", "Zautla", "Zihuateutla", "Zinacatepec", "Zongozotla", "Zoquiapan", "Zoquitlan"};
    String[] Queretaro={"-Seleccionar-", "Amealco de Bonfil", "Arroyo Seco", "Cadereyta de Montes", "Colon", "Corregidora", "El Marques", "Ezequiel Montes", "Huimilpan", "Jalpan de Serra", "Landa de Matamoros", "Pe", "Pedro Escobedo", "Pinal de Amoles", "Queretaro", "San Joaquin", 
            "San Juan del Rio", "Tequisquiapan", "Toliman"};
    String[] QuintanaRoo={"-Seleccionar-", "Bacalar", "Benito Juarez", "Cozumel", "Felipe Carrillo Puerto", "Isla Mujeres", "Jose Maria Morelos", "Lazaro Cardenas", "Othon P. Blanco", "Solidaridad", "Tulum"};
    String[] Sinaloa={"-Seleccionar-", "Ahome", "Angostura", "Badiraguato", "Concordia", "Cosala", "Culiacan", "Choix", "Elota", "Escuinapa", "El Fuerte", "Guasave", "Mazatlan", "Mocorito", "Rosario", "Salvador Alvarado", "San Ignacio", "Sinaloa", "Navolato"};
    String[] Sonara={"-Seleccionar-", "Aconchi", "Agua Prieta", "Alamos", "Altar", "Arivechi", "Arizpe", "Atil", "Bacadehuachi", "Bacanora", "Bacerac", "Bacoachi", "Bacum", "Banamichi", "Baviacora", "Bavispe", "Benito Juarez", "Benjamin Hill", "Caborca", "Cajeme", "Cananea", "Carbo", "Cucurpe", "Cumpas", "Divisaderos", "Empalme", "Etchojoa", "Fronteras", 
            "General Plutarco Elias Calles", "Granados", "Guaymas", "Hermosillo", "Huachinera", "Huasabas", "Huatabampo", "Huepac", "Imuris", "La Colorada", "Magdalena", "Mazatan", "Moctezuma", "Naco", "Nacori Chico", "Nacozari de Garcia", "Navojoa", "Nogales", "Onavas", "Opodepe", "Oquitoa", "Pitiquito", "Puerto Pe", "Quiriego", "Rayon", 
            "Rosario", "Sahuaripa", "San Felipe de Jesus", "San Ignacio Rio Muerto", "San Javier", "San Luis Rio Colorado", "San Miguel de Horcasitas", "San Pedro de la Cueva", "Santa Ana", "Santa Cruz", "Saric", "Soyopa", "Suaqui Grande", "Tepache", "Trincheras", "Tubutama", 
            "Ures", "Villa Hidalgo", "Villa Pesqueira", "Yecora"};
    String[] Tabasco={"-Seleccionar-", "Balancan", "Cardenas", "Centla", "Centro", "Comalcalco", "Cunduacan", "Emiliano Zapata", "Huimanguillo", "Jalapa", "Jalpa de Mendez", "Jonuta", "Macuspana", "Nacajuca", "Paraiso", "Tacotalpa", "Teapa", "Tenosique"};
    String[] Tamaulipas={"-Seleccionar-", "Abasolo", "Aldama", "Altamira", "Antiguo Morelos", "Burgos", "Bustamante", "Camargo", "Casas", "Ciudad Madero", "Cruillas", "El Mante", "Gomez Farias", "Gonzalez", "Guemez", "Guerrero", "Gustavo Diaz Ordaz", "Hidalgo", "Jaumave", "Jimenez", "Llera", "Mainero", "Matamoros", "Mendez", 
            "Mier", "Miguel Aleman", "Miquihuana", "Nuevo Laredo", "Nuevo Morelos", "Ocampo", "Padilla", "Palmillas", "Reynosa", "Rio Bravo", "San Carlos", "San Fernando", "San Nicolas", "Soto la Marina", "Tampico", "Tula", "Valle Hermoso", "Victoria", "Villagran", "Xicotencatl"};
    String[] Tlaxcala={"-Seleccionar-", "Acuamanala de Miguel Hidalgo", "Amaxac de Guerrero", "Apetatitlan de Antonio Carvajal", "Apizaco", "Atlangatepec", "Atltzayanca", "Benito Juarez", "Calpulalpan", "Chiautempan", "Contla de Juan Cuamatzi", "Cuapiaxtla", "Cuaxomulco", "El Carmen Tequexquitla", 
            "Emiliano Zapata", "Espa", "Huamantla", "Hueyotlipan", "Ixtacuixtla de Mariano Matamoros", "Ixtenco", "La Magdalena Tlaltelulco", "Lazaro Cardenas", "Mazatecochco de Jose Maria Morelos", "Mu", "Nanacamilpa de Mariano Arista", "Nativitas", "Panotla", "Papalotla de Xicohtencatl", "San Damian Texoloc", "San Francisco Tetlanohcan", "San Jeronimo Zacualpan", 
            "San Jose Teacalco", "San Juan Huactzinco", "San Lorenzo Axocomanitla", "San Lucas Tecopilco", "San Pablo del Monte", "Sanctorum de Lazaro Cardenas", "Santa Ana Nopalucan", "Santa Apolonia Teacalco", "Santa Catarina Ayometla", "Santa Cruz Quilehtla", "Santa Cruz Tlaxcala", "Santa Isabel Xiloxoxtla", "Tenancingo", "Teolocholco", "Tepetitla de Lardizabal", 
            "Tepeyanco", "Terrenate", "Tetla de la Solidaridad", "Tetlatlahuca", "Tlaxcala", "Tlaxco", "Tocatlan", "Totolac", "Tzompantepec", "Xaloztoc", "Xaltocan", "Xicohtzinco", "Yauhquemehcan", "Zacatelco", "Ziltlaltepec de Trinidad Sanchez Santos"};
    String[] Veracruz={"-Seleccionar-", "Acajete", "Acatlan", "Acayucan", "Actopan", "Acula", "Acultzingo", "Agua Dulce", "Alamo Temapache", "Alpatlahuac", "Alto Lucero de Gutierrez Barrios", "Altotonga", "Alvarado", "Amatitlan", "Amatlan de los Reyes", "Angel R. Cabada", "Apazapan", "Aquila", 
            "Astacinga", "Atlahuilco", "Atoyac", "Atzacan", "Atzalan", "Ayahualulco", "Banderilla", "Benito Juarez", "Boca del Rio", "Calcahualco", "Camaron de Tejeda", "Camerino Z. Mendoza", "Carlos A. Carrillo", "Carrillo Puerto", "Castillo de Teayo", "Catemaco", "Cazones de Herrera", 
            "Cerro Azul", "Chacaltianguis", "Chalma", "Chiconamel", "Chiconquiaco", "Chicontepec", "Chinameca", "Chinampa de Gorostiza", "Chocaman", "Chontla", "Chumatlan", "Citlaltepetl", "Coacoatzintla", "Coahuitlan", "Coatepec", "Coatzacoalcos", "Coatzintla", "Coetzala", "Colipa", "Comapa", 
            "Cordoba", "Cosamaloapan de Carpio", "Cosautlan de Carvajal", "Coscomatepec", "Cosoleacaque", "Cotaxtla", "Coxquihui", "Coyutla", "Cuichapa", "Cuitlahuac", "El Higo", "Emiliano Zapata", "Espinal", "Filomeno Mata", "Fortin", "Gutierrez Zamora", "Hidalgotitlan", "Huatusco", "Huayacocotla", 
            "Hueyapan de Ocampo", "Huiloapan de Cuauhtemoc", "Ignacio de la Llave", "Ilamatlan", "Isla", "Ixcatepec", "Ixhuacan de los Reyes", "Ixhuatlan de Madero", "Ixhuatlan del Cafe", "Ixhuatlan del Sureste", "Ixhuatlancillo", "Ixmatlahuacan", "Ixtaczoquitlan", "Jalacingo", "Jalcomulco", "Jaltipan", 
            "Jamapa", "Jesus Carranza", "Jilotepec", "Jose Azueta", "Juan Rodriguez Clara", "Juchique de Ferrer", "La Antigua", "La Perla", "Landero y Coss", "Las Choapas", "Las Minas", "Las Vigas de Ramirez", "Lerdo de Tejada", "Los Reyes", "Magdalena", "Maltrata", "Manlio Fabio Altamirano", "Mariano Escobedo", 
            "Martinez de la Torre", "Mecatlan", "Mecayapan", "Medellin", "Miahuatlan", "Minatitlan", "Misantla", "Mixtla de Altamirano", "Moloacan", "Nanchital de Lazaro Cardenas del Rio", "Naolinco", "Naranjal", "Naranjos Amatlan", "Nautla", "Nogales", "Oluta", "Omealca", "Orizaba", "Otatitlan", 
            "Oteapan", "Ozuluama de Mascare", "Pajapan", "Panuco", "Papantla", "Paso de Ovejas", "Paso del Macho", "Perote", "Platon Sanchez", "Playa Vicente", "Poza Rica de Hidalgo", "Pueblo Viejo", "Puente Nacional", "Rafael Delgado", "Rafael Lucio", "Rio Blanco", "Saltabarranca", "San Andres Tenejapan", 
            "San Andres Tuxtla", "San Juan Evangelista", "San Rafael", "Santiago Sochiapan", "Santiago Tuxtla", "Sayula de Aleman", "Sochiapa", "Soconusco", "Soledad Atzompa", "Soledad de Doblado", "Soteapan", "Tamalin", "Tamiahua", "Tampico Alto", "Tancoco", "Tantima", "Tantoyuca", "Tatahuicapan de Juarez", 
            "Tatatila", "Tecolutla", "Tehuipango", "Tempoal", "Tenampa", "Tenochtitlan", "Teocelo", "Tepatlaxco", "Tepetlan", "Tepetzintla", "Tequila", "Texcatepec", "Texhuacan", "Texistepec", "Tezonapa", "Tierra Blanca", "Tihuatlan", "Tlachichilco", "Tlacojalpan", "Tlacolulan", "Tlacotalpan", "Tlacotepec de Mejia", 
            "Tlalixcoyan", "Tlalnelhuayocan", "Tlaltetela", "Tlapacoyan", "Tlaquilpa", "Tlilapan", "Tomatlan", "Tonayan", "Totutla", "Tres Valles", "Tuxpan", "Tuxtilla", "Ursulo Galvan", "Uxpanapa", "Vega de Alatorre", "Veracruz", "Villa Aldama", "Xalapa", "Xico", "Xoxocotla", "Yanga", "Yecuatla", "Zacualpan", "Zaragoza", "Zentla", "Zongolica", "Zontecomatlan de Lopez y Fuentes", "Zozocolco de Hidalgo"};
    String[] Yucatan={"-Seleccionar-", "Abala", "Acanceh", "Akil", "Baca", "Bokoba", "Buctzotz", "Cacalchen", "Calotmul", "Cansahcab", "Cantamayec", "Celestun", "Cenotillo", "Chacsinkin", "Chankom", "Chapab", "Chemax", "Chichimila", "Chicxulub Pueblo", "Chikindzonot", "Chochola", "Chumayel", "Conkal", 
            "Cuncunul", "Cuzama", "Dzan", "Dzemul", "Dzidzantun", "Dzilam de Bravo", "Dzilam Gonzalez", "Dzitas", "Dzoncauich", "Espita", "Halacho", "Hocaba", "Hoctun", "Homun", "Huhi", "Hunucma", "Ixil", "Izamal", "Kanasin", "Kantunil", "Kaua", "Kinchil", "Kopoma", "Mama", "Mani", "Maxcanu", "Mayapan", 
            "Merida", "Mococha", "Motul", "Muna", "Muxupip", "Opichen", "Oxkutzcab", "Panaba", "Peto", "Progreso", "Quintana Roo", "Rio Lagartos", "Sacalum", "Samahil", "San Felipe", "Sanahcat", "Santa Elena", "Seye", "Sinanche", "Sotuta", "Sucila", "Sudzal", "Suma", "Tahdziu", "Tahmek", "Teabo", "Tecoh", 
            "Tekal de Venegas", "Tekanto", "Tekax", "Tekit", "Tekom", "Telchac Pueblo", "Telchac Puerto", "Temax", "Temozon", "Tepakan", "Tetiz", "Teya", "Ticul", "Timucuy", "Tinum", "Tixcacalcupul", "Tixkokob", "Tixmehuac", "Tixpehual", "Tizimin", "Tunkas", "Tzucacab", "Uayma", "Ucu", "Uman", "Valladolid", 
            "Xocchel", "Yaxcaba", "Yaxkukul", "Yobain"};
    String[] Zacatecas={"-Seleccionar-", "Apozol", "Apulco", "Atolinga", "Benito Juarez", "Ca", "Calera", "Chalchihuites", "Concepcion del Oro", "Cuauhtemoc", "El Plateado de Joaquin Amaro", "El Salvador", "Fresnillo", "Genaro Codina", "General Enrique Estrada", "General Francisco R. Murguia", "General Panfilo Natera", 
            "Guadalupe", "Huanusco", "Jalpa", "Jerez", "Jimenez del Teul", "Juan Aldama", "Juchipila", "Loreto", "Luis Moya", "Mazapil", "Melchor Ocampo", "Mezquital del Oro", "Miguel Auza", "Momax", "Monte Escobedo", "Morelos", "Moyahua de Estrada", "Nochistlan de Mejia", "Noria de Angeles", "Ojocaliente", 
            "Panuco", "Pinos", "Rio Grande", "Sain Alto", "Santa Maria de la Paz", "Sombrerete", "Susticacan", "Tabasco", "Tepechitlan", "Tepetongo", "Teul de Gonzalez Ortega", "Tlaltenango de Sanchez Roman", "Trancoso", "Trinidad Garcia de la Cadena", "Valparaiso", "Vetagrande", "Villa de Cos", "Villa Garcia", "Villa Gonzalez Ortega", 
            "Villa Hidalgo", "Villanueva", "Zacatecas"};
        
    public String[] CargarMunicipios(String estadoSeleccionado)
    {
        this.estadoSeleccionado = estadoSeleccionado;
        if(estadoSeleccionado.equals("San Luis Potosí"))
                {          
                    return SanLuisPotosi;
                }
        if(estadoSeleccionado.equals("Aguascalientes"))
                {          
                    return Aguascalientes;
                }
        if(estadoSeleccionado.equals("Baja California"))
                {          
                    return BajaCalifornia;
                }
        if(estadoSeleccionado.equals("Baja California Sur"))
                {          
                    return BajaCaliforniaSur;
                }
        if(estadoSeleccionado.equals("Campeche"))
                {          
                    return Campeche;
                }
        if(estadoSeleccionado.equals("Coahuila de Zaragoza"))
                {          
                    return Coahuila;
                }
        if(estadoSeleccionado.equals("Colima"))
                {          
                    return Colima;
                }
        if(estadoSeleccionado.equals("Chiapas"))
                {          
                    return Chiapas;
                }
        if(estadoSeleccionado.equals("Chihuahua"))
                {          
                    return Chihuahua;
                }
        if(estadoSeleccionado.equals("Distrito Federal"))
                {          
                    return DF;
                }
        if(estadoSeleccionado.equals("Durango"))
                {          
                    return Durango;
                }
        if(estadoSeleccionado.equals("Guanajuato"))
                {          
                    return Guanajuato;
                }
        if(estadoSeleccionado.equals("Guerrero"))
                {          
                    return Guerrero;
                }
        if(estadoSeleccionado.equals("Hidalgo"))
                {          
                    return Hidalgo;
                }
        if(estadoSeleccionado.equals("Jalisco"))
                {          
                    return Jalisco;
                }
        if(estadoSeleccionado.equals("México"))
                {          
                    return Mexico;
                }
        if(estadoSeleccionado.equals("Michoacán de Ocampo"))
                {          
                    return Michoacan;
                }
        if(estadoSeleccionado.equals("Morelos"))
                {          
                    return Morelos;
                }
        if(estadoSeleccionado.equals("Nayarit"))
                {          
                    return Nayarit;
                }
        if(estadoSeleccionado.equals("Nuevo León"))
                {          
                    return NuevoLeon;
                }
        if(estadoSeleccionado.equals("Oaxaca"))
                {          
                    return Oaxaca;
                }
        if(estadoSeleccionado.equals("Puebla"))
                {          
                    return Puebla;
                }
        if(estadoSeleccionado.equals("Quintana Roo"))
                {          
                    return QuintanaRoo;
                }
        if(estadoSeleccionado.equals("Queretaro"))
                {          
                    return Queretaro;
                }
        if(estadoSeleccionado.equals("Sinaloa"))
                {          
                    return Sinaloa;
                }
        if(estadoSeleccionado.equals("Sonora"))
                {          
                    return Sonara;
                }
        if(estadoSeleccionado.equals("Tabasco"))
                {          
                    return Tabasco;
                }
        if(estadoSeleccionado.equals("Tamaulipas"))
                {          
                    return Tamaulipas;
                }
        if(estadoSeleccionado.equals("Tlaxcala"))
                {          
                    return Tlaxcala;
                }
        if(estadoSeleccionado.equals("Veracruz de Ignacio de la Llave"))
                {          
                    return Veracruz;
                }
        if(estadoSeleccionado.equals("Yucatán"))
                {          
                    return Yucatan;
                }
        if(estadoSeleccionado.equals("Zacatecas"))
                {          
                    return Zacatecas;
                }
        if(estadoSeleccionado.equals("-Seleccionar-"))
                {          
                    return Defautl;
                }
        
         return Defautl;
    }
}
