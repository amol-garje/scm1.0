console.log("Contact loolg");

const viewContactModal=document.getElementById("view_contact_modal");

const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

const instanceOptions = {
    id: 'view_contact_modal',
    override: true
  };

  const contactModal=new Modal(viewContactModal,options,instanceOptions);

  function openContactModal(){
       contactModal.show();
  }


  function closeContactModal(){
        contactModal.hide();
  }


  function loadContactdata(id){
        console.log(id);


    try{
    
        fetch(`http://localhost:8080/api/contact/${id}`)
        .then(x=>{return x.json()})
        .then(x=>{
            console.log(x);

            document.querySelector("#contact_name").innerHTML=x.name;
            openContactModal();

        }).catch(x=>{
            console.log(x);
        })

    }catch(error){
        console.log("Some Error is Occure");
    }

  }





  // Delete Contact 

  async function deleteContact(id){
    Swal.fire({
        title: "Do you want to Delete the contact?",
        showCancelButton: true,
        confirmButtonText: "Delete",
      }).then((result) => {
        if (result.isConfirmed) {
            const url="http://localhost:8080/user/contact/delete/"+id;
            location.href=url;

        } else if (result.isDenied) {
          Swal.fire("Changes are not saved", "", "info");
        }
      });
  }