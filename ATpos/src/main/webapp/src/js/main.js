$(document).ready(function(){

	if (!window.indexedDB) {
    	window.alert("Your browser doesn't support a stable version of IndexedDB. Such and such feature will not be available.");
	}

	//Open database
	var request = indexedDB.open('TestDB',1);

	//Error
	request.onerror = function(event){
		console.log('Error');
		alert("Why didn't you allow my web app to use IndexedDB?!");
	};

	//Success
	request.onsuccess = function(event){
		console.log('Success: Open Database')
		db = event.target.result;
		//Show Transactions
		showTransactions();
	};

	request.onupgradeneeded = function(event) { 
	  // Save the IDBDatabase interface 
	  var db = event.target.result;

	  // Create an objectStore for this database
	  if(!db.objectStoreNames.contains('transactionsATpos')){
	 	var objectStore = db.createObjectStore('transactionsATpos', { keyPath: "id", autoIncrement:true });
	  	//index id
	  	objectStore.createIndex("product", "product");
	  }
	};

})

//Add Transaction
function addTransaction(){
	var product = $('#Product').val();
	var payMethod = $('#PayMethod').val();
	var refund = $('#Refund').val();
	var bill = $('#Bill').val();
	

	var transactionDB = db.transaction(["transactionsATpos"], "readwrite");
	//Ask for ObjectStore
	var store = transactionDB.objectStore("transactionsATpos");

	//define customer
	var transaction = {
		product: product,
		payMethod: payMethod,
		refund: refund,
		bill: bill
	}

	//Add
	var request = store.add(transaction);

	//Success
	request.onsuccess = function(event){
		window.location.href="index.html";
	}

	//Error
	request.onerror = function(event){
		alert("Transaction was not added");
		console.log('Error', e.target.Error.name);
	}
}

//Display Transactions
function showTransactions(event){
	var transactionsDB = db.transaction(["transactionsATpos"], "readonly");

	//ObjectStorage
	var store = transactionsDB.objectStore("transactionsATpos");
	var index = store.index("product");

	var output = '';
	index.openCursor().onsuccess = function(event){
		var cursor = event.target.result;
		if(cursor){
			output += "<tr class='transaction_"+ cursor.value.id + "'>";
			output += "<td>" + cursor.value.id + "</td>";
			output += "<td><span class='cursor transaction' contenteditable='true' data-field='product' data-id='"+ cursor.value.id + "'>" + cursor.value.product + "</span></td>";
			output += "<td><span class='cursor transaction' contenteditable='true' data-field='payMethod' data-id='"+ cursor.value.id + "'>" + cursor.value.payMethod + "</span></td>";
			output += "<td><span class='cursor transaction' contenteditable='true' data-field='refund' data-id='"+ cursor.value.id + "'>" + cursor.value.refund + "</span></td>";
			output += "<td><span class='cursor transaction' contenteditable='true' data-field='bill' data-id='"+ cursor.value.id + "'>" + cursor.value.bill + "</span></td>";
			output += "<td><a onclick='removeTransaction(" + cursor.value.id + ")' href=''>Delete</td>";
			output += "</tr>";
			cursor.continue();
		}
		$('#transactions').html(output);
	}	
}

//Delete one Transaction
function removeTransaction(id){
	var transactionDB = db.transaction(["transactionsATpos"], "readwrite");

	//Ask for ObjectStore
	var store = transactionDB.objectStore("transactionsATpos");

	var request = store.delete(id);

	//Success
	request.onsuccess = function(){
		console.log('customer ' + id + ' Deleted');
		$(".transaction_"+id).remove();
	}

	//Error
	request.onerror = function(event){
		alert("Transaction was not removed");
		console.log('Error', e.target.Error.name);
	}
}

//Clear All Transactions
function clearTransactions(){
	indexedDB.deleteDatabase('TestDB');
	window.location.href="index.html";
}

//Update Transaction
$('#transactions').on('blur', '.transaction', function(){
	//New entered
	var newText = $(this).html();
	//Field
	var field = $(this).data('field');
	//Transaction ID
	var id = $(this).data('id');

	//Transaction
	var transactionDB = db.transaction(["transactionsATpos"], "readwrite");

	//Ask for ObjectStore
	var store = transactionDB.objectStore("transactionsATpos");

	var request = store.get(id);

	request.onsuccess = function(){
		var data = request.result;
		if(field == 'product'){
			data.product = newText;
		}else if(field == 'payMethod'){
			data.payMethod = newText;
		}else if(field == 'refund'){
			data.refund = newText;
		}else if(field == 'bill'){
			data.bill = newText;
		}

		//Store and Update
		var requestUpdate = store.put(data);

		requestUpdate.onsuccess = function(){
			console.log('Transaction field Updated')
		}

		requestUpdate.onerror = function(){
			console.log('Transaction field was not Updated')
		}
	}
})