let map = L.map("map").setView([42.8746, 74.5698], 12);

L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
	attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

if (document.getElementById("form:object").value != null) {

	let markerEdit = L.marker([document.getElementById("form:lat").value, document.getElementById("form:lng").value]).addTo(map);
	markerEdit.bindPopup(document.getElementById("form:object").value).openPopup();

	L.circle([document.getElementById("form:lat").value, document.getElementById("form:lng").value], {
		color: 'red',
		fillColor: 'red',
		fillOpacity: 0.2,
		radius: document.getElementById("form:danger").value
	}).addTo(map);



	L.circle([document.getElementById("form:lat").value, document.getElementById("form:lng").value], {
		color: 'yellow',
		fillColor: 'yellow',
		fillOpacity: 0.2,
		radius: document.getElementById("form:warning").value
	}).addTo(map);

}

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