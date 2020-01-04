import React, { Component } from 'react'
import ApiService from "../service/ApiService";
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

class EditUserComponent extends Component {

    constructor(props){
        super(props);
        this.state ={
            id: '',
            firstName: '',
            lastName: '',
            userName: '',
            age: '',
            salary: '',
        };
        this.saveUser = this.saveUser.bind(this);
        this.loadUser = this.loadUser.bind(this);
    }

    componentDidMount() {
        this.loadUser();
    }

    loadUser() {
        ApiService.fetchUserById(window.localStorage.getItem("userId"))
            .then((res) => {
                let user = res.data;
                this.setState({
                    id: user.id,
                    userName: user.userName,
                    firstName: user.firstName,
                    lastName: user.lastName,
                    age: user.age,
                    salary: user.salary,
                })
            });
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    saveUser = (e) => {
        e.preventDefault();
        let user = {
            id: this.state.id,
            firstName: this.state.firstName,
            lastName: this.state.lastName,
            userName: this.state.userName,
            age: this.state.age,
            salary: this.state.salary
        };
        ApiService.editUser(user)
            .then(() => {
                this.setState({message : 'User added successfully.'});
                this.props.history.push('/users');
            });
    };

    render() {
        return (
            <div>
                <Typography variant="h4" style={style}>Edit User</Typography>
                <form>
                    <TextField type="text" placeholder="userName" fullWidth margin="normal" name="userName"
                               value={this.state.userName} onChange={this.onChange}/>
                    <TextField placeholder="First Name" fullWidth margin="normal" name="firstName"
                               value={this.state.firstName} onChange={this.onChange}/>
                    <TextField placeholder="Last name" fullWidth margin="normal" name="lastName"
                               value={this.state.lastName} onChange={this.onChange}/>
                    <TextField type="number" placeholder="age" fullWidth margin="normal" name="age"
                               value={this.state.age} onChange={this.onChange}/>
                    <TextField type="number" placeholder="salary" fullWidth margin="normal" name="salary"
                               value={this.state.salary} onChange={this.onChange}/>
                    <Button variant="contained" color="primary" onClick={this.saveUser}>Save</Button>
                </form>
            </div>
        );
    }
}

const style ={
    display: 'flex',
    justifyContent: 'center'
};

export default EditUserComponent;