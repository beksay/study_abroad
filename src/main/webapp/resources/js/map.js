let map = L.map("map").setView([42.8746, 74.5698], 12);

L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
	attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

let marker = {};

function onMapClick(e) {

	if (marker != undefined) {

		map.removeLayer(marker);
	}

	marker = L.marker(e.latlng).addTo(map);
	marker.bindPopup(e.latlng.toString()).openPopup();

	document.getElementById("form:lat").value = e.latlng.lat;
	document.getElementById("form:lng").value = e.latlng.lng;
}

map.on('click', onMapClick);	