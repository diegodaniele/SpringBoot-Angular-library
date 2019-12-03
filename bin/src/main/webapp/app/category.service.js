(function() {
	'use strict';
	angular.module('crudApp').factory('Category', Category);

	Category.$inject = [ '$resource' ];

	function Category($resource) {
		var resourceUrl = 'api/category/:id';

		return $resource(resourceUrl, {}, {
			'update' : {
				method : 'PUT'
			}
		});
	}
})();
