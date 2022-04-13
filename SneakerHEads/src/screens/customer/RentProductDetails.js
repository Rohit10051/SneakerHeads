import "../../App.css"
import Header from '../../components/Header'
import Navigation from "../../components/Navigation";
import Footer from "../../components/Footer";
import { Component } from "react";
import ApiSupplierService from "../../services/supplier/ApiSupplierService";
import ApiCustomerService from "../../services/customer/ApiCustomerService";

export default class RentProductDetails extends Component {
    constructor(props) {
        super(props)
        this.state = {
            st: false,
            productDetails:'',
            bookDuration: window.localStorage.getItem("bookedDuration"),
            date: new Date().toDateString(),
            deposit: '',
            estimatedPrice: '',
            productId: '',
            rentPrice: '',
            status: '',
            deposit: ''
        }
        this.getStatus = this.getStatus.bind(this);
        this.placeOrder = this.placeOrder.bind(this);
        // this.deleteProduct = this.deleteProduct.bind(this);
        // this.addAddress = this.addAddress.bind(this);
    }

    componentDidMount() {
        this.getStatus()
        ApiCustomerService.fetchProductsById(window.localStorage.getItem("product_id"))
            .then((res) => {
                console.log(res.data)
                this.setState({ productDetails : res.data.result })
                this.setState({deposit : this.state.productDetails.price * 0.30})
                this.setState({rentPrice : this.state.productDetails.price * 0.10})
                this.setState({estimatedPrice : this.state.rentPrice * this.state.bookDuration + this.state.deposit})
                
                console.log(this.state.productDetails)
            });
    }
    onChange = e => {
        this.setState({ [e.target.name]: e.target.value });
    }
    getStatus() {
        this.setState(prevState => ({ st: window.localStorage.getItem("status") == 'true' }))
    }
    backToOrderHistory() {
        this.props.history.push('/product-rent');
    }

    addAddress() {
        window.localStorage.setItem("rentProduct",1)
        let size = JSON.parse(window.localStorage.getItem("rentProduct"))
        if (size == 0) {
            alert(" !!! Cart Is Empty !!!")
        }
        if (size !== 0) {
            this.state.st && window.localStorage.setItem("addressStatus", true)
            !this.state.st && this.props.history.push('/login');
            this.state.st && this.props.history.push('/rent-order');
        }
    }
    placeOrder() {
        let size = JSON.parse(window.localStorage.getItem("rentProduct"))
        if (size == 0) {
            alert(" !!! Cart Is Empty !!!")
        }
        if (size !== 0) {
            window.localStorage.setItem("add", this.state.pinCode)
            if (this.state.st && window.localStorage.getItem("addressStatus") == 'false') {
                alert(" !!! Enter Valid Address !!!")
            }
            !this.state.st && this.props.history.push('/login');
            if (window.localStorage.getItem("addressStatus") == 'true') {
                window.localStorage.setItem("addressStatus", false)
                window.localStorage.setItem("rentProduct", true)
                let rent = {
                    date : this.state.date,
                    rent_price : this.state.rentPrice,
                    estimatedPrice: this.state.estimatedPrice,
                    staus : this.state.status,
                    bookDuration : this.state.bookDuration,
                    deposit : this.state.deposit,
                    product : this.state.productDetails.id
                }
                window.localStorage.setItem("rent",JSON.stringify(rent))
                this.state.st && this.props.history.push('/payment');
                !this.state.st && this.props.history.push('/login');
                window.localStorage.setItem("total_price", this.state.estimatedPrice)
            }

        }

    }
    addProduct = (e) => {
        e.preventDefault();

        const product = {
            id: this.state.id,
            productName: this.state.productName,
            description: this.state.description,
            rating: this.state.rating,
            price: this.state.price,
            discount: this.state.discount,
            finalPrice: this.state.price - (this.state.discount * this.state.price / 100),
            qty: 1,
            type: this.state.type

        };



        ApiCustomerService.addProduct(this.state.categoryName, product)
            .then(res => {
                alert("Product Added successfully")
                this.props.history.push('/adminhome');
            });

    };

    render() {
        return (
            <div>
                <Navigation />
                <div className="container">
                    <Header title="Rent Product" />
                    <div className="container border" >
                            {/* {this.state.productDetails.length > 0 && */}
                                <table className="table table-striped">
                                    <thead>
                                        <tr className="float-center">
                                            <th >Product Name</th>
                                            <th>From Date</th>
                                            <th>Book Duration(days)</th>
                                            <th>Deposit</th>
                                            <th>Estimated Price</th>
                                            <th>Rent Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                            <tr>
                                                <td>{this.state.productDetails.productName}</td>
                                                <td>{this.state.date}</td>
                                                <td>{this.state.bookDuration}</td>
                                                <td>{this.state.deposit}</td>
                                                <td>{this.state.estimatedPrice }</td>
                                                <td>{this.state.rentPrice}</td>
                                                <td>
                                                    <button className="btn4 " onClick={() => this.deleteProduct(this.state.productDetails.productName, this.state.productDetails.qty)}>Delete</button>
                                                </td>
                                            </tr>
                                    </tbody>
                                </table>

                            
                        </div>
                        <br />


                        <div className="mb-3">
                            <div className="float-start" >
                                <button className="btn4" onClick={() => this.backToOrderHistory()}>Back</button>
                            </div>
                            <button  className="btn4 float-end" onClick={() => this.addAddress()}>Add Address</button><br /><br />
                            <button className="btn4 float-end" onClick={this.placeOrder}>
                                Rent Product
                            </button>
                            <br></br>

                        </div>
                    <div className="form">
                        
                    </div>
                </div>
            </div>
        );
    }
}