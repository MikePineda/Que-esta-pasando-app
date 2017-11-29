from flask import Flask, abort, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime
import requests
import json

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///D:\GitLab\Proyecto-Final\Server\compas.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)


class User(db.Model):
    username = db.Column(db.String(80), primary_key=True)
    email = db.Column(db.String(120), nullable=True)
    password = db.Column(db.String(80), unique=True, nullable=True)

    def __repr__(self):
        return '<User %r>' % self.username

    def as_dict(self):
        return {c.name: getattr(self, c.name) for c in self.__table__.columns}

class Post(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(80), db.ForeignKey('user.username'), nullable=True)
    message = db.Column(db.String(120), nullable=True)
    date = db.Column(db.DateTime)

    def as_dict(self):
       return {c.name: getattr(self, c.name) for c in self.__table__.columns}

class Compa(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username1 = db.Column(db.String(80), db.ForeignKey('user.username'), nullable=True)
    username2 = db.Column(db.String(80), db.ForeignKey('user.username'), nullable=True)

    def as_dict(self):
       return {c.name: getattr(self, c.name) for c in self.__table__.columns}

@app.route("/getPostsByUser", methods=["GET"])
def getPostsByUser():
    posts = Post.query.filter_by(username=request.args.get('username')).all()
    posts = [post.as_dict() for post in posts]
    return jsonify(posts)

@app.route("/getFriendsOfUser", methods=["GET"])
def getFriendsOfUser():
    compas = Compa.query.filter_by(username1=request.args.get('username')).all()
    compas = [compa.as_dict() for compa in compas]
    return jsonify(compas)

@app.route("/getAllUsers", methods=["GET"])
def getAllUsers():
    users = User.query.all()
    users = [user.as_dict() for user in users]
    return jsonify(users)

@app.route("/getAllPosts", methods=["GET"])
def getAllPosts():
    posts = Post.query.all()
    posts = [post.as_dict() for post in posts]
    return jsonify(posts)

@app.route("/addUser", methods=["POST"])
def addUser():
    if not request.get_json(silent=True):
        abort(400)

    parameters = request.get_json(silent=True)
    user = User(
        username = parameters['username'],
        email = parameters['email'],
        password = parameters['password']
    )
    db.session.add(user)
    try:
        db.session.commit()
        return "Exito"
    except:
        db.session.rollback()
        return "Error"

@app.route("/addPost", methods=["POST"])
def addPost():
    if not request.get_json(silent=True):
        abort(400)

    parameters = request.get_json(silent=True)
    post = Post(
        username = parameters['username'],
        message = parameters['message'],
        date = datetime.now()
    )
    db.session.add(post)
    try:
        db.session.commit()
        return "Exito"
    except:
        db.session.rollback()
        return "Error"
@app.route("/addFriendToUser", methods=["POST"])
def addFriendToUser():
    if not request.get_json(silent=True):
        abort(400)

    parameters = request.get_json(silent=True)
    post = Compa(
        username1 = parameters['compa1'],
        username2 = parameters['compa2']
    )
    db.session.add(post)
    try:
        db.session.commit()
        return "Exito"
    except:
        db.session.rollback()
        return "Error"

if __name__ == "__main__":
    app.run(host= '0.0.0.0')
