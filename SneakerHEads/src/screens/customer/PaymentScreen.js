import React, { Component } from 'react'
import ApiCustomerService from "../../services/customer/ApiCustomerService";
import Navigation from "../../components/Navigation";


class PaymentScreen extends Component {

    constructor(props) {
        super(props)
        this.state ={
            paymentInfo: '',
          message: ''
      }
        this.payment = this.payment.bind(this);
        this.addOrder = this.addOrder.bind(this);
        this.addOrderDetail = this.addOrderDetail.bind(this);
        this.paymentDetails = this.paymentDetails.bind(this);
        this.selectCredit = this.selectCredit.bind(this); 
        this.selectDebit = this.selectDebit.bind(this);
        this.addOrderIdtoOrderAddress = this.addOrderIdtoOrderAddress.bind(this);
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    addOrder(){
        if(window.localStorage.getItem("rentProduct")){
            ApiCustomerService.addRentProduct(window.localStorage.getItem("total_price"), window.localStorage.getItem("user_id"))
            .then(res => {
                console.log("in addRentProduct")
             JSON.stringify(window.localStorage.setItem("orderId", res.data.result))
                this.addRentDetail();
    })
        }
        else{
            ApiCustomerService.addorders(window.localStorage.getItem("total_price"), window.localStorage.getItem("user_id"))
            .then(res => {
                console.log(window.localStorage.getItem("user_id"))
             JSON.stringify(window.localStorage.setItem("orderId", res.data.result))
                this.addOrderDetail();
    })};
    
    }

    addOrderDetail(){
        console.log("in addOrderDetail")
        ApiCustomerService.addDetails(window.localStorage.getItem("user_id"), JSON.parse(window.localStorage.getItem("orderId")))
        .then(res => {
            console.log("in 9 addOrderDetail")
            JSON.stringify(window.localStorage.setItem("deliveryBoyId", res.data.result))
            this.paymentDetails();
    });   
    }
    addRentDetail(){
        console.log("rent details"+window.localStorage.getItem("rent"))
        console.log(JSON.parse(window.localStorage.getItem("bookedDuration")),  JSON.parse(window.localStorage.getItem("product_id")))
       // console.log(bookDuration,productId)
        ApiCustomerService.addRentDetails(window.localStorage.getItem("user_id"), JSON.parse(window.localStorage.getItem("orderId")))
        .then(res => {
            JSON.stringify(window.localStorage.setItem("deliveryBoyId", res.data.result))
            this.paymentDetails();
    });   
    }
    paymentDetails(){
        this.state.payment = {paymentType: this.state.paymentInfo, 
                            deliveryBoyId: JSON.parse(window.localStorage.getItem("user_id")), 
                            orderId : JSON.parse(window.localStorage.getItem("orderId"))};
        ApiCustomerService.addpaymentDetails(this.state.payment);
        this.addOrderIdtoOrderAddress();
    }

    addOrderIdtoOrderAddress(){
        ApiCustomerService.addOrderIdtoOrderAddress(window.localStorage.getItem("address_id"), window.localStorage.getItem("orderId"))
    }

    payment() {
        this.addOrder();
        alert('Payment Done')
        window.localStorage.removeItem("cart_size");
        window.localStorage.removeItem("deliveryBoyId");
        window.localStorage.removeItem("orderId");

        this.props.history.push('/home');
    }

    selectCredit() {
        this.state.paymentInfo= "CREDIT";
    }

    selectDebit() {
        this.state.paymentInfo= "DEBIT";
    }

    render () {
        return (
            <div>
                <Navigation/>
                <div className="payment">
               <div>
                <div className="float-center">
                    <h5>Total Price : {window.localStorage.getItem("total_price")}</h5>
                    <br/>
                    <div className="position1">
                         <div className="dropdown">
                             <a className="btn btn-light dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                             Payment Type
                             </a>
                             <ul className="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                 <li><a className="dropdown-item" onClick={() => { this.selectCredit()}}>CREDIT</a></li>
                                 <li><a className="dropdown-item" onClick={() => { this.selectDebit()}}>DEBIT</a></li>
                             </ul>
                         </div>     
                     </div> 
                     <br/>  

                    <button className="btn4 btn-primary" style={{width:'150px'}} onClick={() => this.payment()}>Payment</button>
                </div>
                </div>
            </div>
            </div>
    
        );
    }
}

export default PaymentScreen